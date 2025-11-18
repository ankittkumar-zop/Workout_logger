package com.example.project_el_dorado.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)