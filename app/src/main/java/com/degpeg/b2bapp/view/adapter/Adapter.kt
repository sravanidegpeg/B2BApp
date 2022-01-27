//package com.degpeg.b2bapp.view.adapter
//
//import android.content.Context
//import android.graphics.Color
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.View.OnLongClickListener
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import com.squareup.picasso.Picasso
//import java.util.*
//
//class RatesAdapter(moviesList: List<Rates?>, context: Context, homeFragment: HomeFragment) :
//    RecyclerView.Adapter<RatesAdapter.MyViewHolder?>() {
//    var moviesList: List<Rates?>
//    var dummyList: HashMap<Int, Rates>? = null
//    private val context: Context
//    var homeFragment: HomeFragment
//
//    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
//        OnLongClickListener, View.OnClickListener {
//        var title: TextView
//        var base_rate: TextView
//        var value: TextView
//        var full_name: TextView
//        var flag_id: ImageView
//        var rates: Rates? = null
//        override fun onLongClick(view: View): Boolean {
//            title.setBackgroundColor(Color.parseColor("black"))
//            return false
//        }
//
//        override fun onClick(view: View) {
//            homeFragment.current_item = rates
//            Collections.swap(moviesList, getAdapterPosition(), 0)
//            notifyItemMoved(getAdapterPosition(), 0)
//            homeFragment.recyclerView.scrollToPosition(getAdapterPosition())
//            homeFragment.set_current_item(rates, getAdapterPosition())
//            Log.e("pos2", "" + getLayoutPosition())
//            Session.setCurrencyID(context, rates.short_name)
//        }
//
//        init {
//            title = view.findViewById<View>(R.id.currency_symbol) as TextView
//            base_rate = view.findViewById<View>(R.id.currency_baserate) as TextView
//            value = view.findViewById<View>(R.id.currency_converted_value) as TextView
//            full_name = view.findViewById<View>(R.id.currency_full_form) as TextView
//            flag_id = view.findViewById<View>(R.id.currency_flag) as ImageView
//            view.setOnClickListener(this)
//        }
//    }
//
//    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val itemView: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.rates_item, parent, false)
//        return MyViewHolder(itemView)
//    }
//
//    fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        if (position < moviesList.size) {
//            val rates: Rates? = moviesList[position]
//            holder.rates = rates
//            holder.title.setText(rates.short_name)
//            // 1 usd/rates.base_rate  * current =  short_name;
//            holder.base_rate.text =
//                "1 " + rates.short_name.toString() + " = " + format_decimals(1 / rates.base_rate.toFloat() * homeFragment.current_item.base_rate.toFloat()) + " " + homeFragment.current_item.short_name
//            holder.value.setText(rates.symbol.toString() + " " + rates.value)
//            holder.full_name.setText(rates.long_name)
//            //  holder.flag_id.setImageResource(rates.flag_id);
//            Picasso.with(context).load(rates.image).into(holder.flag_id)
//        } else {
//        }
//    }
//
//    fun getItemCount(): Int {
//        return moviesList.field
//    }
//
//    private fun format_decimals(value: Float): String {
//        return String.format(Locale.ENGLISH, "%.4f", value)
//    }
//
//    init {
//        this.moviesList = moviesList
//        this.context = context
//        this.homeFragment = homeFragment
//    }
//}