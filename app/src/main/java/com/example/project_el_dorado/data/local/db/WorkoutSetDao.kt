package com.example.project_el_dorado.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.project_el_dorado.data.local.entity.WorkoutSet
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSetDao {
    @Insert
    suspend fun addSet(set: WorkoutSet)

    @Delete
    suspend fun deleteSet(set: WorkoutSet)

    @Query("SELECT * FROM workout_set WHERE exerciseId= :id ORDER BY id DESC")
    fun getSetsFromExercise(id: Int): Flow<List<WorkoutSet>>
}