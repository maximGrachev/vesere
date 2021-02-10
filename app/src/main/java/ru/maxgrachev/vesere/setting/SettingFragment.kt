package ru.maxgrachev.vesere.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.allrecords.AllRecordsViewModel
import ru.maxgrachev.vesere.allrecords.AllRecordsViewModelFactory
import ru.maxgrachev.vesere.database.allrecords.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentAllRecordsBinding
import ru.maxgrachev.vesere.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSettingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao
        val viewModelFactory = SettingViewModelFactory(dataSource, application)
        val settingViewModel = ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)

        settingViewModel.allRecordsDeleted.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Toast.makeText(context, "All events were deleted", Toast.LENGTH_SHORT).show()
                settingViewModel.allRecordsDeletedToFalse()
            }
        })
        binding.settingViewModel = settingViewModel
        return binding.root
    }
}