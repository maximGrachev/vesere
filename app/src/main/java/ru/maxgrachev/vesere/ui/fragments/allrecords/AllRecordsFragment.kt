package ru.maxgrachev.vesere.ui.fragments.allrecords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.VesereApplication
import ru.maxgrachev.vesere.allrecords.AllRecordsAdapter
import ru.maxgrachev.vesere.allrecords.DeleteClickListener
import ru.maxgrachev.vesere.allrecords.EditClickListener
import ru.maxgrachev.vesere.allrecords.EventListener
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.databinding.FragmentAllRecordsBinding

class AllRecordsFragment : Fragment() {
    private lateinit var binding: FragmentAllRecordsBinding
    private lateinit var adapter: AllRecordsAdapter
    private lateinit var allRecordsViewModel: AllRecordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_records, container, false)

        binding.buttonCreateAllRecords.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(AllRecordsFragmentDirections.actionAllRecordsFragmentToNewRecordFragment())
        }

        val args = AllRecordsFragmentArgs.fromBundle(requireArguments())
        val application = requireContext().applicationContext as VesereApplication
        val dataSource = application.categoryRepository
        val viewModelFactory = AllRecordsViewModelFactory(dataSource, application, args)
        allRecordsViewModel =
            ViewModelProvider(this, viewModelFactory).get(AllRecordsViewModel::class.java)
        adapter = AllRecordsAdapter(
            EventListener { categoryID ->
                allRecordsViewModel.onEventClicked(categoryID)
            },
            DeleteClickListener { category: Category ->
                allRecordsViewModel.deleteEvent(category)

            },
            EditClickListener { categoryId ->
                allRecordsViewModel.onEditEventClicked(categoryId)
            }
        )

        binding.listRecords.adapter = adapter

        allRecordsViewModel.records.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        allRecordsViewModel.navigateToEventDetail.observe(viewLifecycleOwner, Observer { category ->
            category?.let {
                this.findNavController()
                    .navigate(
                        AllRecordsFragmentDirections.actionAllRecordsFragmentToEventDetailsFragment(
                            category
                        )
                    )
                allRecordsViewModel.onEventDetailsNavigated()
            }
        })

        allRecordsViewModel.navigateToEditEvent.observe(viewLifecycleOwner, Observer { category ->
            category?.let {
                this.findNavController().navigate(
                    AllRecordsFragmentDirections.actionAllRecordsFragmentToEditRecordFragment(
                        it
                    )
                )
                allRecordsViewModel.onEditEventNavigated()
            }
        })

        binding.lifecycleOwner = this
        binding.allRecordsViewModel = allRecordsViewModel

        inTouchMove() //TODO add delete and edit icons on move
        return binding.root
    }

    private fun inTouchMove() {
        val helper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val position = viewHolder.adapterPosition
                    val myEvent: Category = adapter.getItem(position)

                    allRecordsViewModel.deleteEvent(myEvent)
                }
            })

        helper.attachToRecyclerView(binding.listRecords)
    }
}