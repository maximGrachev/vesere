//package ru.maxgrachev.vesere.ui.fragments.main.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import ru.maxgrachev.vesere.data.local.entity.Category
//import ru.maxgrachev.vesere.databinding.ListItemOneRecordBinding
//
//class MainAdapter(
//    private val clickListener: CategoryListener
//) : ListAdapter<Category, MainAdapter.ViewHolder>(MainDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item!!, clickListener)
//    }
//
//    class ViewHolder private constructor(val binding: ListItemOneRecordBinding):
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(
//            item: Category,
//            clickListener: CategoryListener) {
//            binding.category = item
//            binding.clickListener = clickListener
//            binding.executePendingBindings()
//        }
//
//    }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ListItemOneRecordBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//}
//
//class MainDiffCallback: DiffUtil.ItemCallback<Category>() {
//    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
//        return newItem==oldItem
//    }
//
//}
//
//class CategoryListener(val clickListener: (id: Int?) -> Unit) {
//    fun onClick(category: Category) = clickListener(category.id)
//}
//
//
//
