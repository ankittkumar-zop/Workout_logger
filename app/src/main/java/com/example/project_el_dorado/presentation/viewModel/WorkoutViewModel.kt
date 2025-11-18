package com.example.project_el_dorado.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_el_dorado.data.local.db.AppDatabase
import com.example.project_el_dorado.data.local.entity.Exercise
import com.example.project_el_dorado.data.local.entity.WorkoutSet
import com.example.project_el_dorado.domain.repo.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkoutViewModel(context: Context): ViewModel() {
    private val db = AppDatabase.getInstance(context)
    private val workoutRepo = WorkoutRepository(db.exerciseDao(), db.workoutSetDao())

    val exercises: StateFlow<List<Exercise>> = workoutRepo.exercises.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
    )

    private val _currentExerciseId = MutableStateFlow<Int?>(null)

    val sets: StateFlow<List<WorkoutSet>> =
       _currentExerciseId.flatMapLatest { id ->
           id?.let { workoutRepo.getSets(it) } ?: flowOf(emptyList())
       }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun selectExercise(id: Int){
        _currentExerciseId.value = id
    }

    fun addExercise(name: String) = viewModelScope.launch {
        workoutRepo.addExercise(name)
    }

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch {
        workoutRepo.deleteExercise(exercise = exercise)
    }

    fun addSet(exerciseId: Int, reps: Int, weight: Float) = viewModelScope.launch {
        workoutRepo.addSet(exerciseId = exerciseId, reps = reps, weight = weight)
    }

    fun deleteSet(set: WorkoutSet) = viewModelScope.launch {
        workoutRepo.deleteSet(set)
    }
}