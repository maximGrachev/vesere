package ru.maxgrachev.vesere.newrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.allrecords.AllRecordsViewModel
import ru.maxgrachev.vesere.allrecords.AllRecordsViewModelFactory
import ru.maxgrachev.vesere.database.allrecords.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentNewRecordBinding

class NewRecordFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewRecordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_record, container, false)
        binding.buttonCreateNewRec.setOnClickListener{v:View ->
            v.findNavController().navigate(R.id.action_newRecordFragment_to_allRecordsFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao
        val viewModelFactory = NewRecordViewModelFactory(dataSource, application)
        val newRecordViewModel = ViewModelProvider(this, viewModelFactory).get(NewRecordViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.newRecordViewModel = newRecordViewModel


        return binding.root
    }
}