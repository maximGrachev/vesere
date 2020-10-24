package ru.maxgrachev.vesere.title

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )
        binding.buttonCreateTitle.setOnClickListener{v:View->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToNewRecordFragment())
        }

        binding.buttonOil.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Oil change"))
        }

        binding.buttonAntifreeze.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Antifreeze change"))
        }

        binding.buttonMaintenance.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Maintenance"))
        }

        binding.buttonDiagnostics.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Computer diagnostics"))
        }

        binding.buttonBrake.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Brake repair"))
        }

        binding.buttonEngineWork.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Engine work"))
        }

        binding.buttonElectrical.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Electrical Systems"))
        }

        binding.buttonOther.setOnClickListener{v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAllRecordsFragment("Other"))
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}




