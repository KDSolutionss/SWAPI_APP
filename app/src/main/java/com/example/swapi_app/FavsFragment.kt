package com.example.swapi_app

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MediatorLiveData
import androidx.navigation.fragment.findNavController
import com.example.swapi_app.adapter.StartAdapter
import com.example.swapi_app.database.MyViewModel
import com.example.swapi_app.database.ViewModelFactory
import com.example.swapi_app.databinding.FragmentFavsBinding
import com.example.swapi_app.network.Typeable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavsFragment : Fragment() {

    lateinit var binding: FragmentFavsBinding

    val viewModelka: MyViewModel
            by activityViewModels { ViewModelFactory(Application()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavsBinding.inflate(inflater)
        binding.goToStart.setOnClickListener { findNavController().navigate(R.id.action_favsFragment_to_startFragment) }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModelka.update()
        val mediator = MediatorLiveData<List<Typeable>>()

        mediator.addSource(viewModelka.persons)
        {
            val currentList = mediator.value.orEmpty()
            val combinedList = currentList + it
            mediator.value = combinedList
        }
        mediator.addSource(viewModelka.starships)
        {
            val currentList = mediator.value.orEmpty()
            val combinedList = currentList + it
            mediator.value = combinedList
        }


        mediator.addSource(viewModelka.planets)
        {
            val currentList = mediator.value.orEmpty()
            val combinedList = currentList + it
            mediator.value=combinedList
        }
        val a=0
        mediator.observe(this) {
            binding.recyclerView.adapter = StartAdapter(it, viewModelka,false)
            Log.d("tag",it.toString())
        }

    }
}