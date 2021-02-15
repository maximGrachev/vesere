package ru.maxgrachev.vesere.editrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.convertLongToDateString
import ru.maxgrachev.vesere.database.allrecords.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentEditRecordBinding
import ru.maxgrachev.vesere.eventdetail.EventDetailsViewModel
import ru.maxgrachev.vesere.eventdetail.EventDetailsViewModelFactory
import ru.maxgrachev.vesere.newrecord.NewRecordFragmentDirections
import java.util.*

class EditRecordFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val binding: FragmentEditRecordBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit_record, container, false)

        val args = EditRecordFragmentArgs.fromBundle(requireArguments())

        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao
        val editRecordViewModelFactory= EditRecordViewModelFactory(args.eventKey, dataSource)

        val editRecordViewModel = ViewModelProvider(this, editRecordViewModelFactory)
            .get(EditRecordViewModel::class.java)

        binding.editRecordViewModel = editRecordViewModel

        binding.lifecycleOwner = this

        //Material design data picker
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        binding.editTextDateEditRecord.setOnClickListener() {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date").build()
            // set listener when date is selected
            datePicker.addOnPositiveButtonClickListener {
                // Create calendar object and set the date to be that returned from selection
                calendar.time = Date(it)
                binding.editTextDateEditRecord.setText(convertLongToDateString(calendar.timeInMillis))
            }
            datePicker.show(childFragmentManager, "pickedDate")
        }

        binding.buttonUpdateRecord.setOnClickListener {v: View ->
            if (binding.editTextMaintenanceTaskEditRecord.text.isEmpty()) {
                Toast.makeText(context, R.string.enter_maintenance_task, Toast.LENGTH_SHORT).show()
//                binding.editTextMaintenanceTaskEditRecord.setHintTextColor(R.color.light_red) //TODO change color if TextMaintenanceTask isn't edited
            } else {
                v.findNavController().navigate(EditRecordFragmentDirections.actionEditRecordFragmentToAllRecordsFragment("All")
                )
                editRecordViewModel.getEvent().value?.let {
                    editRecordViewModel.updateRecord(
                        it.eventID,
                        binding.editTextMaintenanceTaskEditRecord.text.toString(),
                        calendar.timeInMillis,
                        binding.editTextServiceLifeEditRecord.text.toString(),
                        binding.editTextMileageValueEditRecord.text.toString(),
                        binding.editTextPriceEditRecord.text.toString(),
                        binding.editTextServiceLifeEditRecord.text.toString(),
                        binding.editTextCommentEditRecord.text.toString(),
                        binding.switchRatingEditRecord.isChecked
                    )
                }
            }
        }
        return binding.root
    }
}