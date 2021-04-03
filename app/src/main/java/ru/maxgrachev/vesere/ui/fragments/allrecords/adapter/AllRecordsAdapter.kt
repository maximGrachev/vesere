package ru.maxgrachev.vesere.allrecords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters
import ru.maxgrachev.vesere.databinding.ListItemOneRecordBinding

class AllRecordsAdapter(
    private val clickListener: EventListener,
    private val deleteClickListener: DeleteClickListener,
    private val editClickListener: EditClickListener
) :
    ListAdapter<CategoryWithParameters, AllRecordsAdapter.ViewHolder>(AllRecordsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    public override fun getItem(position: Int): CategoryWithParameters {
        return super.getItem(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!, clickListener, deleteClickListener, editClickListener)
    }

    class ViewHolder private constructor(val binding: ListItemOneRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: CategoryWithParameters,
            clickListener: EventListener,
            deleteClickListener: DeleteClickListener,
            editClickListener: EditClickListener
        ) {
            binding.eventWithParameter = item
            binding.clickListener = clickListener
            binding.deleteClickListener = deleteClickListener
            binding.editClickListener = editClickListener
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

class AllRecordsDiffCallback : DiffUtil.ItemCallback<CategoryWithParameters>() {
    override fun areItemsTheSame(oldItem: CategoryWithParameters, newItem: CategoryWithParameters): Boolean {
        return oldItem.category.id == newItem.category.id
    }

    override fun areContentsTheSame(oldItem: CategoryWithParameters, newItem: CategoryWithParameters): Boolean {
        return oldItem.category == newItem.category
    }

}

class EventListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(event: CategoryWithParameters) = clickListener(event.category.id!!)
}

class DeleteClickListener(val deleteClickListener: (event: CategoryWithParameters) -> Unit) {
    fun onClick(event: CategoryWithParameters) = deleteClickListener(event)
}

class EditClickListener(val editClickListener: (id: Int) -> Unit) {
    fun onClick(event: CategoryWithParameters) = editClickListener(event.category.id!!)
}

