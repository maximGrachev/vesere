package ru.maxgrachev.vesere.allrecords

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.TextItemViewHolder
import ru.maxgrachev.vesere.database.allrecords.Event

class AllRecordsAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Event>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_one_record, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.eventName
    }

}