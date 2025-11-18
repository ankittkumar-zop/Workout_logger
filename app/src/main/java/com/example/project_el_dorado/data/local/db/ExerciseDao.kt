package com.example.project_el_dorado.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.project_el_dorado.data.local.entity.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    suspend fun addExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise ORDER BY id DESC")
    fun getExercises(): Flow<List<Exercise>>
}