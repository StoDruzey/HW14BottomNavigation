package com.example.hw14bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw14bottomnavigation.RecyclerView.PetListAdapter
import com.example.hw14bottomnavigation.RoomDatabase.RoomPet
import com.example.hw14bottomnavigation.RoomDatabase.appDatabase
import com.example.hw14bottomnavigation.databinding.FragmentPageBinding

class PageFragment : Fragment() {

    private var _binding: FragmentPageBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val petDao by lazy {
        requireContext().appDatabase.petDao()
    }

    private val adapter by lazy {
        PetListAdapter(
            context = requireContext()
        ) {
            val direction = PageFragmentDirections
                .actionFragmentPageToFragmentFillDb(it.id)
            findNavController()
                .navigate(direction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentPageBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = adapter
            val pets = loadPets()
            adapter.submitList(pets)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadPets():List<RoomPet> {
        return petDao.loadPetsAll()
    }
}