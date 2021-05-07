package com.muz1i.diseasereportandroid.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

/**
 * @author: Muz1i
 * @date: 2021/5/6
 */
class MyArrayAdapter(context: Context, id: Int, items: List<String>) :
    ArrayAdapter<String>(context, id, items) {

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                return FilterResults()
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {}
        }
    }
}
