package ru.maxgrachev.vesere.allrecords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.convertLongToDateString
import ru.maxgrachev.vesere.database.allrecords.Event

class AllRecordsAdapter: RecyclerView.Adapter<AllRecordsAdapter.ViewHolder>() {
    var data = listOf<Event>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val textEvent: TextView = itemView.findViewById(R.id.text_event)
        val textData: TextView = itemView.findViewById(R.id.text_date)
        val buttonEdit: Button = itemView.findViewById(R.id.button_edit)
        val buttonDelete: Button = itemView.findViewById(R.id.button_delete)

        fun bind(item: Event) {
            textEvent.text = item.eventName
            textData.text = convertLongToDateString(item.dateMilli)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_one_record, parent, false)
                return ViewHolder(view)
            }
        }
    }
}

