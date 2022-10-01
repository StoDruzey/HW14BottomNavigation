package com.example.hw14bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hw14bottomnavigation.RoomDatabase.RoomPet
import com.example.hw14bottomnavigation.RoomDatabase.appDatabase
import com.example.hw14bottomnavigation.databinding.FragmentFilldatabaseBinding
import com.example.hw14bottomnavigation.extensions.getTextOrSetError

class FillDatabaseFragment : Fragment() {
    private var _binding: FragmentFilldatabaseBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val petDao by lazy {
        requireContext().appDatabase.petDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFilldatabaseBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateDatabase()

        with(binding) {
            buttonAdd.setOnClickListener {
                val petType = containerPet.getTextOrSetError()
                val petBreed = containerPetBreed.getTextOrSetError()
                val petName = containerPetName.getTextOrSetError()

                if (petType == null || petBreed == null || petName == null) return@setOnClickListener
                petDao.insertAll(RoomPet(petType = petType, petBreed = petBreed, petName = petName))
                updateDatabase()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateDatabase() {
        with(binding) {
            resultInfo.text = petDao.loadPetsAll().joinToString("\n")
            containerPet.error = null
            containerPetBreed.error = null
            containerPetName.error = null
        }
    }
}