package com.example.project.characterbuilder


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.charactermanager.CharacterManagerFragmentDirections
import com.example.project.database.Character
import com.example.project.database.CharacterDatabase
import com.example.project.databinding.FragmentCharacterBuilderBinding

/**
 * A simple [Fragment] subclass.
 */
class CharacterBuilderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCharacterBuilderBinding>(
            inflater,
            R.layout.fragment_character_builder, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = CharacterDatabase.getInstance(application).characterDAO
        val parties = CharacterDatabase.getInstance(application).partyDAO
        val viewModelFactory = CharacterBuilderViewModelFactory(dataSource, parties)

        val characterBuilderViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(CharacterBuilderViewModel::class.java)

        binding.characterBuilderViewModel = characterBuilderViewModel

        binding.setLifecycleOwner(this)

        val adapter = ArrayAdapter.createFromResource(
            application.applicationContext,
            R.array.classes,
            android.R.layout.simple_list_item_1
        )
        binding.classAutocomplete.setAdapter(adapter)

        characterBuilderViewModel.navigateToCharacterManager.observe(this, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    CharacterBuilderFragmentDirections
                        .actionCharacterBuilderFragmentToCharacterManagerFragment()
                )
                characterBuilderViewModel.doneNavigating()
            }
        })

        return binding.root
    }


}
