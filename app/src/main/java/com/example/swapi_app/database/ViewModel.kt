package com.example.swapi_app.database

import android.app.Application
import androidx.lifecycle.*
import com.example.swapi_app.network.Person
import com.example.swapi_app.network.Planet
import com.example.swapi_app.network.Starship

class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MyViewModel(app: Application) : ViewModel() {
    private val personDao = ItemRoomDatabase.getDatabase(app).personDao()
    private val planetDao = ItemRoomDatabase.getDatabase(app).planetDao()
    private val starshipDao = ItemRoomDatabase.getDatabase(app).starshipDao()

    var persons: LiveData<List<Person>> =
        personDao.getAll().map {
            it.map { x ->
                Person(
                    x.name,
                    x.gender,
                    x.films.split(","),
                    x.films.split(",")
                )
            }
        }
    var planets: LiveData<List<Planet>> =
        planetDao.getAll()
            .map { it.map { x -> Planet(x.name, x.diameter, x.population, x.films.split(",")) } }
    var starships: LiveData<List<Starship>> =
        starshipDao.getAll().map {
            it.map { x ->
                Starship(
                    x.name,
                    x.model,
                    x.manufaturer,
                    x.passengers,
                    x.films.split(",")
                )
            }
        }


    fun insertPerson(entity: PersonEntity) {
        personDao.insert(entity)
    }

    fun insertPlanet(entity: PlanetEntity) {
        planetDao.insert(entity)
    }

    fun insertStarship(entity: StarshipEntity) {
        starshipDao.insert(entity)
    }

    fun update() {
        persons =
            personDao.getAll().map {
                it.map { x ->
                    Person(
                        x.name,
                        x.gender,
                        x.starships.split(","),
                        x.films.split(",")
                    )
                }
            }
        planets =
            planetDao.getAll()
                .map {
                    it.map { x ->
                        Planet(
                            x.name,
                            x.diameter,
                            x.population,
                            x.films.split(",")
                        )
                    }
                }
        starships =
            starshipDao.getAll().map {
                it.map { x ->
                    Starship(
                        x.name,
                        x.model,
                        x.manufaturer,
                        x.passengers,
                        x.films.split(",")
                    )
                }
            }
    }


}