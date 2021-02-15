package ru.maxgrachev.vesere.editrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.database.allrecords.EventDatabase
import ru.maxgrachev.vesere.databinding.FragmentEditRecordBinding
import ru.maxgrachev.vesere.eventdetail.EventDetailsViewModel
import ru.maxgrachev.vesere.eventdetail.EventDetailsViewModelFactory

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

        binding.buttonUpdateRecord.setOnClickListener {
            if (binding.editTextMaintenanceTaskEditRecord.text.isEmpty()) {
                Toast.makeText(context, R.string.enter_maintenance_task, Toast.LENGTH_SHORT).show()
                binding.editTextMaintenanceTaskEditRecord.setHintTextColor(R.color.light_red)
            } else {
//                it.findNavController().navigate()
//                newRecordViewModel.createNewRecord(
//                    binding.editTextMaintenanceTask.text.toString(),
//                    binding.editTextServiceLife.text.toString(),
//                    binding.editTextMileageValue.text.toString(),
//                    binding.editTextPrice.text.toString(),
//                    binding.editTextTextServiceName.text.toString(),
//                    binding.editTextComment.text.toString(),
//                    binding.switchRating.isChecked
//                )
            }
        }
        return binding.root
    }
}