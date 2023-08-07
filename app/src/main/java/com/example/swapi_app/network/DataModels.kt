package com.example.swapi_app.network

import com.example.swapi_app.data.DataType
import com.squareup.moshi.Json

interface Typeable {
    val type: DataType
}

data class ResponsePerson(
    @Json(name = "count") val count: String,
    @Json(name = "results") val results: List<PersonNetwork>
)

data class ResponsePlanet(
    @Json(name = "count") val count: String,
    @Json(name = "results") val results: List<PlanetNetwork>
)

data class ResponseStarship(
    @Json(name = "count") val count: String,
    @Json(name = "results") val results: List<StarshipNetwork>
)

data class PersonNetwork(
    @field:Json(name = "name") val firstField: String,
    @field:Json(name = "gender") val secondField: String,
    @field:Json(name = "starships") val thirdField: List<String>,
    @field:Json(name = "films") val fifthField: List<String>,
)

data class Person(
    val name: String,
    val gender: String,
    val starships: List<String>,
    val films: List<String>,
    override val type: DataType = DataType.PERSON
) : Typeable

data class StarshipNetwork(
    @field:Json(name = "name") val firstField: String,
    @field:Json(name = "model") val secondField: String,
    @field:Json(name = "manufacturer") val thirdField: String,
    @field:Json(name = "passengers") val fourthField: String,
    @field:Json(name = "films") val fifthField: List<String>,
)

data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    val films: List<String>,
    override val type: DataType = DataType.STARSHIP
) : Typeable

data class PlanetNetwork(
    @field:Json(name = "name") val firstField: String,
    @field:Json(name = "diameter") val secondField: String,
    @field:Json(name = "population") val thirdField: String,
    @field:Json(name = "films") val fifthField: List<String>,
    )

data class Planet(
    val name: String,
    val diameter: String,
    val population: String,
    val films: List<String>,
    override val type: DataType = DataType.PLANET
) : Typeable

data class FilmNetwork(
    @field:Json(name = "title") val firstField: String,
    @field:Json(name = "director") val secondField: String,
    @field:Json(name = "producer") val thirdField: String,
)

data class Film(
    val title: String,
    val director: String,
    val producer: String,
    override val type: DataType = DataType.FILM
) : Typeable
