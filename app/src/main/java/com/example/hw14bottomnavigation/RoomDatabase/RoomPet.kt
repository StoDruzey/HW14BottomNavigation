package com.example.hw14bottomnavigation.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomPet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "type")
    val petType: String,
    @ColumnInfo(name = "breed")
    val petBreed: String,
    @ColumnInfo(name = "name")
    val petName: String
)