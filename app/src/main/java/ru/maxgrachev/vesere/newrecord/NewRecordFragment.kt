package ru.maxgrachev.vesere.newrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.maxgrachev.vesere.R
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
        return binding.root
    }
}