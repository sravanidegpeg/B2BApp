package com.degpeg.b2bapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.degpeg.b2bapp.Constants
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit

class AppClient {
    var netWorkService: ApiInterface? = null
    val HEADER_CACHE_CONTROL = "Cache-Control"
    val HEADER_PRAGMA = "Pragma"
    private var mContext: Context? = null

    fun ApiClient(context: Context) {
        mContext = context
        val httpCacheDirectory = File(context.cacheDir, "cache_file")
        val cache = Cache(httpCacheDirectory, 40 * 1024 * 1024)
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(provideOfflineCacheInterceptor())
            .addNetworkInterceptor(provideCacheInterceptor())
            .cache(provideCache())
            .build()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()) // Important to set a client on the build to prevent .build() from making new ones
            .client(okHttpClient)
        netWorkService =
            retrofitBuilder.baseUrl(Constants.BASE_URL).build().create(ApiInterface::class.java)
    }

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(
                File(mContext!!.cacheDir, "http-cache"),
                40 * 1024 * 1024
            )
        } catch (e: Exception) {
            Log.d("ApiClient", "Could not create Cache!")
        }
        return cache
    }

    private fun provideCacheInterceptor(): Interceptor? {
        return Interceptor { chain: Interceptor.Chain ->
            val response = chain.proceed(chain.request())
            val cacheControl: CacheControl
            cacheControl = if (isConnected()) {
                CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build()
            } else {
                CacheControl.Builder()
                    .maxStale(7, TimeUnit.SECONDS)
                    .build()
            }
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun provideOfflineCacheInterceptor(): Interceptor? {
        return Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            if (!isConnected()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.SECONDS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }


    fun isConnected(): Boolean {
        try {
            val e = mContext!!.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val activeNetwork = e.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        } catch (e: Exception) {
            Log.w("ApiClient", e.toString())
        }
        return false
    }
}