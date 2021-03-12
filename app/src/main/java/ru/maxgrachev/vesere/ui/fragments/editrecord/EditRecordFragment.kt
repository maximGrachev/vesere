package ru.maxgrachev.vesere.ui.fragments.editrecord

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.data.local.database.AppRoomDatabase
import ru.maxgrachev.vesere.databinding.FragmentEditRecordBinding
import ru.maxgrachev.vesere.utils.convertLongToDateString
import java.util.*

class EditRecordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val binding: FragmentEditRecordBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit_record, container, false
        )

        val args = EditRecordFragmentArgs.fromBundle(requireArguments())
        val scope  = CoroutineScope(SupervisorJob())
        val dataSource = AppRoomDatabase.getInstance(application,scope).categoryDao
        val editRecordViewModelFactory = EditRecordViewModelFactory(args.eventKey, dataSource)

        val editRecordViewModel = ViewModelProvider(this, editRecordViewModelFactory)
            .get(EditRecordViewModel::class.java)

        binding.editRecordViewModel = editRecordViewModel

        binding.lifecycleOwner = this

        // AutoCompleteTextView in the layout
        val eventsArray =
            resources.getStringArray(R.array.events_array) //TODO get events_array from repo
        val adapter =
            ArrayAdapter(requireContext(), R.layout.list_maintenance_task_item, eventsArray)
        (binding.editTextMaintenanceTaskEditRecord.editText as? AutoCompleteTextView)?.setAdapter(
            adapter
        )

        //Material design data picker
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        binding.editTextDateEditRecord.setOnClickListener() {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date").build()
            // set listener when date is selected
            datePicker.addOnPositiveButtonClickListener {
                // Create calendar object and set the date to be that returned from selection
                calendar.time = Date(it)
                binding.editTextDateEditRecord.editText?.setText(convertLongToDateString(calendar.timeInMillis))
            }
            datePicker.show(childFragmentManager, "pickedDate")
        }

        binding.buttonUpdateRecord.setOnClickListener { v: View ->
            if (binding.editTextMaintenanceTaskEditRecord.editText?.text.toString().isEmpty()) {
                Toast.makeText(context, R.string.enter_maintenance_task, Toast.LENGTH_SHORT).show()
//                binding.editTextMaintenanceTaskEditRecord.setHintTextColor(R.color.light_red) //TODO change color if TextMaintenanceTask isn't edited
            } else {
                v.findNavController().navigate(
                    EditRecordFragmentDirections.actionEditRecordFragmentToAllRecordsFragment("All")
                )
                editRecordViewModel.getEvent().value?.let {
                    editRecordViewModel.updateRecord(
                        it.eventID,
                        binding.editTextMaintenanceTaskEditRecord.editText?.text.toString(),
                        calendar.timeInMillis,
                        binding.editTextServiceLifeEditRecord.editText?.text.toString(),
                        binding.editTextMileageValueEditRecord.editText?.text.toString(),
                        binding.editTextPriceEditRecord.editText?.text.toString(),
                        binding.editTextTextServiceNameEditRecord.editText?.text.toString(),
                        binding.editTextCommentEditRecord.editText?.text.toString(),
                        binding.switchRatingEditRecord.isChecked
                    )
                }
            }
        }
        return binding.root
    }
}