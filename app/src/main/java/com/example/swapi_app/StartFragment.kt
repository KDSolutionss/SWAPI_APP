package com.example.swapi_app


import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.swapi_app.adapter.StartAdapter
import com.example.swapi_app.data.DataType
import com.example.swapi_app.data.Repository
import com.example.swapi_app.database.MyViewModel
import com.example.swapi_app.database.ViewModelFactory
import com.example.swapi_app.databinding.FragmentStartBinding
import com.example.swapi_app.network.*
import kotlinx.coroutines.launch


class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding
    lateinit var type: String
    val repo = Repository()
    val viewModelka: MyViewModel
            by activityViewModels { ViewModelFactory(Application()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        binding.goToFavs.setOnClickListener { findNavController().navigate(R.id.action_startFragment_to_favsFragment) }
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if ((p0?.length ?: 0) > 1) {
                    Log.d("visibilty", "visible")
                    binding.submitButton.visibility = View.VISIBLE
                } else {
                    binding.submitButton.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        val options = arrayOf(
            DataType.PERSON.name,
            DataType.STARSHIP.name,
            DataType.PLANET.name,
        )
        val spinnerAdapter = activity?.let {
            ArrayAdapter(
                it,
                R.layout.spinner_item,
                options
            )
        }
        spinnerAdapter?.setDropDownViewResource(R.layout.spinner_item)

        val spinner = binding.optionsSpinner
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                type = options[position]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        spinner.setSelection(0)
        spinner.setPrompt("Type")
        activity?.title = "Search fragment"
        binding.submitButton.setOnClickListener {
            binding.loadingAnimation.visibility = View.VISIBLE
            Log.d("tag", binding.recyclerView.adapter?.itemCount.toString())
            lifecycleScope.launch {
                when (type) {
                    "PERSON" -> {
                        val aboba = repo.loadPersons(binding.editText.text.toString())

                        val personsList =
                            aboba.map {
                                val films = it.fifthField.map { s ->
                                    val xz = s.takeLast(2).first().toString();
                                    repo.loadFilm(xz)
                                }
                                val starships = it.thirdField.map { s ->
                                    val xz = s.takeLast(3).take(2).toString();
                                    repo.loadStarship_unit(xz)
                                }
                                Log.d("oper", films.toString())
                                Person(
                                    it.firstField,
                                    it.secondField,
                                    starships.map { it -> "${it.firstField}" },
                                    films.map { it -> "Name:${it.firstField},Director:${it.secondField},Producer:${it.thirdField}" }
                                )
                            }
                        binding.recyclerView.adapter =
                            StartAdapter(personsList, viewModel = viewModelka)
                        binding.recyclerView.adapter?.notifyDataSetChanged()
                    }
                    "STARSHIP" -> {
                        val aboba = repo.loadStarships(binding.editText.text.toString())
                        val starshipsList =
                            aboba.map {
                                val films = it.fifthField.map { s ->
                                    val xz = s.takeLast(2).first().toString();
                                    repo.loadFilm(xz)
                                }
                                Starship(
                                    it.firstField,
                                    it.secondField,
                                    it.thirdField,
                                    it.fourthField,
                                    films.map { it -> "Name:${it.firstField}+Director:${it.secondField}+Producer:${it.thirdField}" }
                                )
                            }
                        binding.recyclerView.adapter = StartAdapter(starshipsList, viewModelka)
                        binding.recyclerView.adapter?.notifyDataSetChanged()
                    }
                    "PLANET" -> {
                        val aboba = repo.loadPlanets(binding.editText.text.toString())
                        val planetsList =
                            aboba.map {
                                val films = it.fifthField.map { s ->
                                    val xz = s.takeLast(2).first().toString();
                                    repo.loadFilm(xz)
                                }

                                Planet(
                                    it.firstField,
                                    it.secondField,
                                    it.thirdField,
                                    films.map { it -> "Name:${it.firstField}+Director:${it.secondField}+Producer:${it.thirdField}" }
                                )

                            }
                        binding.recyclerView.adapter = StartAdapter(planetsList, viewModelka)
                        binding.recyclerView.adapter?.notifyDataSetChanged()
                    }
                }
                binding.loadingAnimation.visibility = View.GONE;
            }
        }


        return binding.root
    }
//    override fun onStart() {
//        super.onStart()
//        viewModelka.update()
//        viewModelka.quotes.observe(
//            viewLifecycleOwner
//        ) { binding.recyclerView.adapter = viewModelka.quotes.value?.let { FavsAdapter(it) } }
//
//    }
}