package com.example.hw14bottomnavigation.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomPet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): RoomPetDao
}