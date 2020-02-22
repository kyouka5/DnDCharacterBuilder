package com.example.project.characterviewer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.database.CharacterDatabase
import com.example.project.databinding.FragmentCharacterViewerBinding

/**
 * A simple [Fragment] subclass.
 */
class CharacterViewerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCharacterViewerBinding>(
            inflater,
            R.layout.fragment_character_viewer, container, false
        )

        val application = requireNotNull(this.activity).application

        val characters = CharacterDatabase.getInstance(application).characterDAO
        val parties = CharacterDatabase.getInstance(application).partyDAO
        val viewModelFactory = CharacterViewerViewModelFactory(
            characters,
            parties,
            CharacterViewerFragmentArgs.fromBundle(arguments!!).characterId
        )

        val characterViewerViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(CharacterViewerViewModel::class.java)

        binding.characterViewerViewModel = characterViewerViewModel

        binding.setLifecycleOwner(this)

        characterViewerViewModel.navigateOnDelete.observe(this, Observer {
            if (it == true) {
                characterViewerViewModel.delete()
                this.findNavController().navigate(
                    CharacterViewerFragmentDirections.actionCharacterViewerFragmentToCharacterManagerFragment()
                )
                characterViewerViewModel.doneNavigating()
            }
        })

        characterViewerViewModel.character.observe(this, Observer {
            characterViewerViewModel.setPartyById(it.partyId)
        })

        return binding.root
    }


}
