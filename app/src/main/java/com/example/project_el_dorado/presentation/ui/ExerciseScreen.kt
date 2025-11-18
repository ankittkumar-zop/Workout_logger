package com.example.project_el_dorado.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_el_dorado.data.local.entity.Exercise
import com.example.project_el_dorado.presentation.viewModel.WorkoutViewModel

@Composable
fun ExerciseScreen(
    viewModel: WorkoutViewModel,
    onSelect: (Exercise) -> Unit
) {
    val exercises by viewModel.exercises.collectAsState()

    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Exercise Name") }
        )

        Button(
            onClick = {
                if (name.isNotBlank()) {
                    viewModel.addExercise(name)
                    name = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Exercise")
        }

        LazyColumn {
            items(exercises) { exercise ->
                Row(
                    Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(exercise.name)
                    Row {
                        Button(onClick = { onSelect(exercise) }) {
                            Text("Open")
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = { viewModel.deleteExercise(exercise) }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}


