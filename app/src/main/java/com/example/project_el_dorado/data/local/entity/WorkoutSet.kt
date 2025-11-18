package com.example.project_el_dorado.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "workout_set",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("exerciseId")])
data class WorkoutSet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val exerciseId: Int,
    val reps: Int,
    val weight: Float
)