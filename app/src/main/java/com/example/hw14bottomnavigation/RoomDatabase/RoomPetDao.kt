package com.example.hw14bottomnavigation.RoomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomPetDao {
    @Query("SELECT * FROM roompet")
    fun loadPetsAll(): List<RoomPet>

    @Query("SELECT * FROM roompet WHERE id IN (:petIds)")
    fun loadPetsByIds(petIds: IntArray): List<RoomPet>

    @Query("SELECT * FROM roompet WHERE type LIKE :petType")
    fun loadPetsByType(petType: String): List<RoomPet>

    @Query("SELECT * FROM roompet WHERE type LIKE :petType AND name LIKE :petName")
    fun findPetsByTypeAndName(petType: String, petName: String): List<RoomPet>

    @Insert
    fun insertAll(vararg pets: RoomPet)

    @Delete
    fun deletePet(pet: RoomPet)
}