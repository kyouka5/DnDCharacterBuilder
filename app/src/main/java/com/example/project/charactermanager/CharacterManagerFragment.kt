package com.example.project.charactermanager


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.database.CharacterDatabase
import com.example.project.databinding.FragmentCharacterManagerBinding

/**
 * A simple [Fragment] subclass.
 */
class CharacterManagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCharacterManagerBinding>(
            inflater,
            R.layout.fragment_character_manager, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = CharacterDatabase.getInstance(application).characterDAO
        val viewModelFactory = CharacterManagerViewModelFactory(dataSource)

        val characterManagerViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(CharacterManagerViewModel::class.java)

        binding.characterManagerViewModel = characterManagerViewModel

        val adapter = CharacterAdapter(CharacterListener { characterId ->
            characterManagerViewModel.onCharacterClicked(characterId)
        })
        binding.recyclerView.adapter = adapter

        characterManagerViewModel.allCharacters.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        characterManagerViewModel.navigateToCharacterViewer.observe(this, Observer { characterId ->
            characterId?.let {
                this.findNavController().navigate(
                    CharacterManagerFragmentDirections
                        .actionCharacterManagerFragmentToCharacterViewerFragment(characterId)
                )
                characterManagerViewModel.onCharacterViewerNavigated()
            }
        })

        characterManagerViewModel.navigateToCharacterBuilder.observe(this, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    CharacterManagerFragmentDirections.actionCharacterManagerFragmentToCharacterBuilderFragment()
                )
                characterManagerViewModel.onCharacterBuilderNavigated()
            }
        })

        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.character_manager_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.findNavController()
            .navigate(CharacterManagerFragmentDirections.actionCharacterManagerFragmentToOverviewFragment())
        return true
    }

}
