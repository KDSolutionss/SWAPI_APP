package com.example.swapi_app.data

import com.example.swapi_app.network.*

class Repository {
    suspend fun loadFilm(string: String): FilmNetwork {
        return Service.retrofitService.getFilms(string)
    }

    suspend fun loadStarship_unit(string: String): StarshipNetwork {
        return Service.retrofitService.getStarship_unit(string)
    }
    suspend fun loadPersons(string: String): List<PersonNetwork> {
        return Service.retrofitService.getPerson(string).results
    }

    suspend fun loadStarships(string: String): List<StarshipNetwork> {
        return Service.retrofitService.getStarship(string).results
    }

    suspend fun loadPlanets(string: String): List<PlanetNetwork> {
        return Service.retrofitService.getPlanet(string).results
    }
}