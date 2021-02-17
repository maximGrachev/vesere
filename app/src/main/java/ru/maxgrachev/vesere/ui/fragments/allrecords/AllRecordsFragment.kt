package ru.maxgrachev.vesere.ui.fragments.allrecords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.allrecords.AllRecordsAdapter
import ru.maxgrachev.vesere.allrecords.DeleteClickListener
import ru.maxgrachev.vesere.allrecords.EditClickListener
import ru.maxgrachev.vesere.allrecords.EventListener
import ru.maxgrachev.vesere.data.local.database.EventDatabase
import ru.maxgrachev.vesere.data.local.entity.Event
import ru.maxgrachev.vesere.databinding.FragmentAllRecordsBinding

class AllRecordsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAllRecordsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_records, container, false)

        binding.buttonCreateAllRecords.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(AllRecordsFragmentDirections.actionAllRecordsFragmentToNewRecordFragment())
        }

        val args = AllRecordsFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao
        val viewModelFactory = AllRecordsViewModelFactory(dataSource, application, args)
        val allRecordsViewModel =
            ViewModelProvider(this, viewModelFactory).get(AllRecordsViewModel::class.java)
        val adapter = AllRecordsAdapter(
            EventListener { eventID ->
                allRecordsViewModel.onEventClicked(eventID)
            },
            DeleteClickListener { event: Event ->
                allRecordsViewModel.deleteEvent(event)

            },
            EditClickListener { eventId ->
                allRecordsViewModel.onEditEventClicked(eventId)
            }
        )

        binding.listRecords.adapter = adapter

        allRecordsViewModel.records.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        allRecordsViewModel.navigateToEventDetail.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
                this.findNavController()
                    .navigate(
                        AllRecordsFragmentDirections.actionAllRecordsFragmentToEventDetailsFragment(
                            event
                        )
                    )
                allRecordsViewModel.onEventDetailsNavigated()
            }
        })

        allRecordsViewModel.navigateToEditEvent.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
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
        return binding.root
    }
}