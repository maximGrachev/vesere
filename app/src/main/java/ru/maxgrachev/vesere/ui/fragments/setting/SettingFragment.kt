package ru.maxgrachev.vesere.ui.fragments.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.data.local.database.AppRoomDatabase
import ru.maxgrachev.vesere.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSettingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        val application = requireNotNull(this.activity).application
        val scope  = CoroutineScope(SupervisorJob())
        val dataSource = AppRoomDatabase.getInstance(application, scope).categoryDao
        val viewModelFactory = SettingViewModelFactory(dataSource, application)
        val settingViewModel =
            ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)

        settingViewModel.allRecordsDeleted.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete all records")
                    .setMessage("Do you want to delete all your records?")
                    .setNegativeButton("No") { dialog, which ->
                        dialog.cancel()
                    }
                    .setPositiveButton("Yes") { dialog, which ->
                        settingViewModel.allRecordsDeletedToFalse()
                        dialog.cancel()
                        Toast.makeText(context, "All events were deleted", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .show()


            }
        })
        binding.settingViewModel = settingViewModel
        return binding.root
    }
}