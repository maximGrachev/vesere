package ru.maxgrachev.vesere.ui.fragments.newrecord

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_new_record.*
import kotlinx.android.synthetic.main.fragment_new_record.view.*
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.data.local.database.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentNewRecordBinding
import ru.maxgrachev.vesere.utils.convertLongToDateString
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
        val eventsArray = resources.getStringArray(R.array.events_array) //TODO get events_array from repo
        val adapter = ArrayAdapter(requireContext(), R.layout.list_maintenance_task_item, eventsArray)
        (binding.editTextMaintenanceTask.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        //Material design data picker
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        binding.clicableEditTextDate.setOnClickListener() {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date").build()
            // set listener when date is selected
            datePicker.addOnPositiveButtonClickListener {
                // Create calendar object and set the date to be that returned from selection
                calendar.time = Date(it)
                binding.editTextDate.editText?.setText(convertLongToDateString(calendar.timeInMillis))
            }
            datePicker.show(childFragmentManager, "pickedDate")
        }


        binding.buttonCreateNewRec.setOnClickListener { v: View ->
            if (binding.editTextMaintenanceTask.editText?.text.toString().isEmpty()) {
                Toast.makeText(context, R.string.enter_maintenance_task, Toast.LENGTH_SHORT).show()
//                binding.editTextMaintenanceTask.setHintTextColor(R.color.light_red) //TODO change color if TextMaintenanceTask isn't edited
            } else {
                v.findNavController().navigate(
                    NewRecordFragmentDirections.actionNewRecordFragmentToAllRecordsFragment("All")
                )
                newRecordViewModel.createNewRecord(
                    binding.editTextMaintenanceTask.editText?.text.toString(),
                    calendar.timeInMillis,
                    binding.editTextServiceLife.editText?.text.toString(),
                    binding.editTextMileageValue.editText?.text.toString(),
                    binding.editTextPrice.editText?.text.toString(),
                    binding.editTextTextServiceName.editText?.text.toString(),
                    binding.editTextComment.editText?.text.toString(),
                    binding.switchRating.isChecked
                )
            }
        }
        return binding.root
    }
}