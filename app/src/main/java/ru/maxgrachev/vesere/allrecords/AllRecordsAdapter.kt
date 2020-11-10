package ru.maxgrachev.vesere.allrecords

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.maxgrachev.vesere.convertLongToDateString
import ru.maxgrachev.vesere.database.allrecords.Event
import ru.maxgrachev.vesere.databinding.ListItemOneRecordBinding

class AllRecordsAdapter: ListAdapter<Event, AllRecordsAdapter.ViewHolder>(AllRecordsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemOneRecordBinding): RecyclerView.ViewHolder(binding.root){
        val buttonEdit: Button = binding.buttonEdit
        val buttonDelete: Button = binding.buttonDelete

        fun bind(item: Event) {
            binding.event = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemOneRecordBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AllRecordsDiffCallback: DiffUtil.ItemCallback<Event>(){
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.eventID == newItem.eventID
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

}

