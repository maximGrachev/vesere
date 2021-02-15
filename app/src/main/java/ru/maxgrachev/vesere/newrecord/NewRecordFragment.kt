package ru.maxgrachev.vesere.newrecord

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.convertLongToDateString
import ru.maxgrachev.vesere.database.allrecords.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentNewRecordBinding
import java.util.*


class NewRecordFragment : Fragment() {
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewRecordBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_record, container, false)

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

        //Material design data picker
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        binding.editTextDate.setOnClickListener() {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date").build()
            // set listener when date is selected
            datePicker.addOnPositiveButtonClickListener {
                // Create calendar object and set the date to be that returned from selection
                calendar.time = Date(it)
                binding.editTextDate.setText(convertLongToDateString(calendar.timeInMillis))
            }
            datePicker.show(childFragmentManager, "pickedDate")
        }


        binding.buttonCreateNewRec.setOnClickListener { v: View ->
            if (binding.editTextMaintenanceTask.text.isEmpty()) {
                Toast.makeText(context, R.string.enter_maintenance_task, Toast.LENGTH_SHORT).show()
//                binding.editTextMaintenanceTask.setHintTextColor(R.color.light_red) //TODO change color if TextMaintenanceTask isn't edited
            } else {
                v.findNavController().navigate(
                    NewRecordFragmentDirections.actionNewRecordFragmentToAllRecordsFragment("All")
                )
                newRecordViewModel.createNewRecord(
                    binding.editTextMaintenanceTask.text.toString(),
                    calendar.timeInMillis,
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