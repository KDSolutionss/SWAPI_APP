package com.example.swapi_app.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT * FROM people")
    fun getAll(): LiveData<List<PersonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quote: PersonEntity)

}
@Dao
interface PlanetDao {
    @Query("SELECT * FROM planets")
    fun getAll(): LiveData<List<PlanetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quote: PlanetEntity)

}
@Dao
interface StarshipDao {
    @Query("SELECT * FROM starships")
    fun getAll(): LiveData<List<StarshipEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quote: StarshipEntity)

}