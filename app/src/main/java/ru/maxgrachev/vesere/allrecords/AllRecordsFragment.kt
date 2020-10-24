package ru.maxgrachev.vesere.allrecords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.database.allrecords.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentAllRecordsBinding

class AllRecordsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAllRecordsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_records, container, false)
        binding.buttonCreateAllRecords.setOnClickListener{v: View ->
            v.findNavController().navigate(AllRecordsFragmentDirections.actionAllRecordsFragmentToNewRecordFragment())
        }

        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao
        val viewModelFactory = AllRecordsViewModelFactory(dataSource, application)
        val allRecordsViewModel = ViewModelProvider(this, viewModelFactory).get(AllRecordsViewModel::class.java)
        val args = AllRecordsFragmentArgs.fromBundle(requireArguments())


        binding.setLifecycleOwner(this)
        binding.allRecordsViewModel = allRecordsViewModel
        return binding.root
    }
}