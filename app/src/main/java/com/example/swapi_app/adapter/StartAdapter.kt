package com.example.swapi_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.swapi_app.data.DataType
import com.example.swapi_app.database.MyViewModel
import com.example.swapi_app.database.PersonEntity
import com.example.swapi_app.database.PlanetEntity
import com.example.swapi_app.database.StarshipEntity
import com.example.swapi_app.databinding.ListItemFilmBinding
import com.example.swapi_app.databinding.ListItemPersonBinding
import com.example.swapi_app.databinding.ListItemPlanetBinding
import com.example.swapi_app.databinding.ListItemStarshipBinding
import com.example.swapi_app.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StartAdapter(
    private val dataset: List<Typeable>,
    val viewModel: MyViewModel,
    val showButton: Boolean = true
) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (dataset[position].type) {
            DataType.PERSON -> 0
            DataType.STARSHIP -> 1
            DataType.PLANET -> 2
            DataType.FILM -> 3
        }
    }

    inner class ListItemPersonViewHolder(private val person: ListItemPersonBinding) :
        ViewHolder(person.root) {
        fun bind(item: Typeable) {
            person.textView1.text = "Name:${(item as Person).name}"
            person.textView2.text = "Gender:${item.gender}"
            person.textView3.text = "Starships:${item.starships}"
            if (showButton) {
                person.addToFavs.visibility = View.VISIBLE

            } else {
                person.addToFavs.visibility = View.INVISIBLE
                person.textView4.text = "Films:${item.films}"
            }
            person.addToFavs.setOnClickListener {

                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.insertPerson(
                            PersonEntity(
                                item.name,
                                item.gender,
                                item.starships.toString(),
                                item.films.toString()
                            )
                        )
                    }
                }
            }
        }
    }

    inner class ListItemStarshipViewHolder(private val starship: ListItemStarshipBinding) :
        ViewHolder(starship.root) {
        fun bind(item: Typeable) {
            starship.textView1.text = "Name${(item as Starship).name}"
            starship.textView2.text = "Model:${item.model}"
            starship.textView3.text = "Manufacturer:${item.manufacturer}"
            starship.textView4.text = "Count of passengers:${(item).passengers}"
            if (showButton) {
                starship.addToFavs.visibility = View.VISIBLE

            } else {
                starship.textView5.text = "Films:${item.films}"
                starship.addToFavs.visibility = View.INVISIBLE
            }
            starship.addToFavs.setOnClickListener {

                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.insertStarship(
                            StarshipEntity(
                                item.name,
                                item.model,
                                item.manufacturer,
                                item.films.toString(),
                                item.passengers
                            )
                        )
                    }
                }
            }

        }
    }

    inner class ListItemPlanetViewHolder(private val planet: ListItemPlanetBinding) :
        ViewHolder(planet.root) {
        fun bind(item: Typeable) {
            planet.textView1.text = "Name${(item as Planet).name}"
            planet.textView2.text = "Diameter${item.diameter}"
            planet.textView3.text = "Population${item.population}"
            if (showButton) {
                planet.addToFavs.visibility = View.VISIBLE
            } else {
                planet.textView4.text = "Films:${item.films}"
                planet.addToFavs.visibility = View.INVISIBLE
            }

            planet.addToFavs.setOnClickListener {

                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.insertPlanet(
                            PlanetEntity(
                                item.name,
                                item.diameter,
                                item.population,
                                item.films.toString()
                            )
                        )
                    }
                }
            }

        }
    }

    inner class ListItemFilmViewHolder(private val film: ListItemFilmBinding) :
        ViewHolder(film.root) {
        fun bind(item: Typeable) {
            film.textView1.text = (item as Film).title
            film.textView2.text = item.director
            film.textView3.text = item.producer

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> {
                val a = ListItemPersonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ListItemPersonViewHolder(a)
            }
            1 -> {
                val a = ListItemStarshipBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ListItemStarshipViewHolder(a)
            }
            2 -> {
                val a = ListItemPlanetBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ListItemPlanetViewHolder(a)
            }
            3 -> {
                val a =
                    ListItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListItemFilmViewHolder(a)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> {
                (holder as ListItemPersonViewHolder).bind(dataset[position])
            }
            1 -> {
                (holder as ListItemStarshipViewHolder).bind(dataset[position])
            }
            2 -> {
                (holder as ListItemPlanetViewHolder).bind(dataset[position])
            }
            3 -> {
                (holder as ListItemFilmViewHolder).bind(dataset[position])
            }
        }
    }

}