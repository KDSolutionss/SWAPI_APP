package com.example.swapi_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PersonEntity(
    val name: String,
    val gender: String,
    val starships: String,
    val films: String,
    @PrimaryKey val id: Int = name.hashCode() / gender.hashCode() * starships.hashCode()
)

@Entity(tableName = "starships")
data class StarshipEntity(
    val name: String,
    val model: String,
    val manufaturer: String,
    val films: String,
    val passengers: String,
    @PrimaryKey val id: Int = name.hashCode() / model.hashCode() * manufaturer.hashCode()
)
@Entity(tableName = "planets")
data class PlanetEntity(
    val name: String,
    val diameter: String,
    val population: String,
    val films:String,
    @PrimaryKey val id: Int = name.hashCode() / diameter.hashCode() * population.hashCode()
)