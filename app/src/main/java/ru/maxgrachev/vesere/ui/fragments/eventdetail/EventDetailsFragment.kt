package ru.maxgrachev.vesere.ui.fragments.eventdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.data.local.database.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentEventDetailsBinding

class EventDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEventDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_event_details, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = EventDetailsFragmentArgs.fromBundle(requireArguments())

        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao
        val eventDetailsModelFactory = EventDetailsViewModelFactory(arguments.eventKey, dataSource)

        val eventDetailsViewModel = ViewModelProvider(
            this, eventDetailsModelFactory
        ).get(EventDetailsViewModel::class.java)

        binding.eventDetailsViewModel = eventDetailsViewModel

        binding.lifecycleOwner = this

        eventDetailsViewModel.navigateToAllRecords.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToAllRecordsFragment("All")
                )
            }
        })

        return binding.root
    }
}