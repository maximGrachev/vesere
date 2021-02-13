package ru.maxgrachev.vesere.newrecord

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.maxgrachev.vesere.R
import android.widget.Toast
import ru.maxgrachev.vesere.database.allrecords.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentNewRecordBinding

class NewRecordFragment : Fragment() {
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewRecordBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_record, container, false)

        var switchRatingValue: Boolean = false
        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao
        val viewModelFactory = NewRecordViewModelFactory(dataSource, application)
        val newRecordViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewRecordViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.newRecordViewModel = newRecordViewModel

        // AutoCompleteTextView in the layout
        val eventNameTextView = binding.editTextMaintenanceTask as AutoCompleteTextView
        val eventsArray: Array<out String> = resources.getStringArray(R.array.events_array)
        val adapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item,
                eventsArray
            )
        }
        eventNameTextView.setAdapter(adapter)


        binding.buttonCreateNewRec.setOnClickListener { v: View ->
            if (binding.editTextMaintenanceTask.text.isEmpty()) {
                Toast.makeText(context, R.string.enter_maintenance_task, Toast.LENGTH_SHORT).show()
                binding.editTextMaintenanceTask.setTextColor(R.color.light_red)
            } else {
                v.findNavController().navigate(
                    NewRecordFragmentDirections.actionNewRecordFragmentToAllRecordsFragment("All")
                )
                newRecordViewModel.createNewRecord(
                    binding.editTextMaintenanceTask.text.toString(),
                    binding.editTextServiceLife.text.toString(),
                    binding.editTextMileageValue.text.toString(),
                    binding.editTextPrice.text.toString(),
                    binding.editTextTextServiceName.text.toString(),
                    binding.editTextComment.text.toString(),
                    binding.switchRating.isChecked
                )
            }
        }
        return binding.root
    }
}