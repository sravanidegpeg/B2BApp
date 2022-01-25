package com.degpeg.b2bapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.squareup.picasso.Picasso
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.degpeg.b2bapp.view.activity.MenuItem
import com.example.b2bapp.R
import java.util.regex.Pattern


class MenuAdapter(mainActivity: Context, categories: ArrayList<MenuItem>) :
    BaseAdapter(), Filterable {
    var context: Context = mainActivity
    var categories: ArrayList<MenuItem>
    var categories_all: ArrayList<MenuItem>
    var planetFilter: PlanetFilter? = null
    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return categories.size
    }

    override fun getItem(position: Int): Any {
        // TODO Auto-generated method stub
        return position
    }

    override fun getItemId(position: Int): Long {
        // TODO Auto-generated method stub
        return position.toLong()
    }

    inner class Holder {
        var menu_title: TextView? = null
        var cur_name: TextView? = null
        var menu_icon: ImageView? = null
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // TODO Auto-generated method stub
        val holder: Holder = Holder()
        val rowView: View = LayoutInflater.from(context).inflate(R.layout.menu_adapter, null)!!
        holder.menu_title = rowView.findViewById(R.id.menu_item)
        holder.menu_title?.text = categories[position].title
        holder.menu_icon = rowView.findViewById(R.id.menu_icon) as ImageView
        Picasso.get().load(categories[position].image).into(holder.menu_icon)
        return rowView
    }

    open inner class PlanetFilter : Filter() {
        var clear_all = false
        protected override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            // We implement here the filter logic
            clear_all = false
            if (constraint == null || constraint.isEmpty()) {
                clear_all = true
                // No filter implemented we return all the list
                results.values = categories
                results.count = categories.size
            } else {
// We perform filtering operation
                val nPlanetList: MutableList<MenuItem> = ArrayList()
                for (p in categories_all) {

//Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();
                    if (Pattern.compile(
                            Pattern.quote(constraint.toString()),
                            Pattern.CASE_INSENSITIVE
                        ).matcher(p.toString()).find()
                    ) nPlanetList.add(p)
                }
                results.values = nPlanetList
                results.count = nPlanetList.size
            }
            return results
        }

         override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            if (results.count === 0) {
//                restaurants = (ArrayList<Restaurants>) results.values;
                notifyDataSetChanged()
            } else if (clear_all) {
                categories = categories_all
                notifyDataSetChanged()
            } else {
                categories = results.values as ArrayList<MenuItem>
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        private var inflater: LayoutInflater? = null
    }

    init {
        // TODO Auto-generated constructor stubcontext=mainActivity;
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        this.categories = categories
        categories_all = categories
    }

    override fun getFilter(): Filter {
        if (planetFilter == null) planetFilter = PlanetFilter()
        return planetFilter as PlanetFilter
    }
}