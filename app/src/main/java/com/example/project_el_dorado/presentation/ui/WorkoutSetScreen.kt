package com.example.project_el_dorado.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.project_el_dorado.data.local.entity.Exercise
import com.example.project_el_dorado.presentation.viewModel.WorkoutViewModel


@Composable
fun WorkoutSetScreen(
    viewModel: WorkoutViewModel,
    exercise: Exercise
) {
    val sets by viewModel.sets.collectAsState()

    var reps by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Exercise: ${exercise.name}", fontSize = 20.sp)

        OutlinedTextField(
            value = reps,
            onValueChange = { reps = it },
            label = { Text("Reps") }
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") }
        )

        Button(
            onClick = {
                val r = reps.toIntOrNull()
                val w = weight.toFloatOrNull()
                if (r != null && w != null) {
                    viewModel.addSet(exercise.id, r, w)
                    reps = ""
                    weight = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Set")
        }

        LazyColumn {
            items(sets) { set ->
                Row(
                    Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${set.reps} reps × ${set.weight} kg")
                    Button(onClick = { viewModel.deleteSet(set) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
