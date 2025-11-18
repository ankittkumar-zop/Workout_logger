package com.example.project_el_dorado.domain.repo

import com.example.project_el_dorado.data.local.db.ExerciseDao
import com.example.project_el_dorado.data.local.db.WorkoutSetDao
import com.example.project_el_dorado.data.local.entity.Exercise
import com.example.project_el_dorado.data.local.entity.WorkoutSet

class WorkoutRepository(
    private val exerciseDao: ExerciseDao,
    private val workoutSetDao: WorkoutSetDao
) {
    val exercises = exerciseDao.getExercises()

    fun getSets(exerciseId: Int) = workoutSetDao.getSetsFromExercise(exerciseId)

    suspend fun addExercise(name: String) = exerciseDao.addExercise(Exercise(name = name))

    suspend fun deleteExercise(exercise: Exercise) = exerciseDao.deleteExercise(exercise = exercise)

    suspend fun addSet(exerciseId: Int, reps: Int, weight: Float) = workoutSetDao.addSet(WorkoutSet(exerciseId = exerciseId, reps = reps, weight = weight))

    suspend fun deleteSet(set: WorkoutSet) = workoutSetDao.deleteSet(set)
}