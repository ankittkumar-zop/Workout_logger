package com.example.project_el_dorado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project_el_dorado.data.local.entity.Exercise
import com.example.project_el_dorado.presentation.ui.ExerciseScreen
import com.example.project_el_dorado.presentation.ui.WorkoutSetScreen
import com.example.project_el_dorado.presentation.viewModel.WorkoutViewModel
import com.example.project_el_dorado.ui.theme.ProjecteldoradoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = WorkoutViewModel(applicationContext)

        setContent {
            var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

            selectedExercise?.let { exercise ->
                WorkoutSetScreen(
                    viewModel = viewModel,
                    exercise = exercise
                )
            } ?: run {
                ExerciseScreen(
                    viewModel = viewModel,
                    onSelect = {
                        selectedExercise = it
                        viewModel.selectExercise(it.id)
                    }
                )
            }
        }
    }
}
