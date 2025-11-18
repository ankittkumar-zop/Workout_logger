package com.example.project_el_dorado.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.project_el_dorado.data.local.entity.Exercise
import com.example.project_el_dorado.data.local.entity.WorkoutSet
import com.example.project_el_dorado.presentation.viewModel.WorkoutViewModel


@Composable
fun WorkoutSetScreen(
    viewModel: WorkoutViewModel, exercise: Exercise
) {
    val sets by viewModel.sets.collectAsState()

    var reps by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = exercise.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
        ) {
            Column(Modifier.padding(16.dp)) {

                OutlinedTextField(
                    value = reps,
                    onValueChange = { reps = it },
                    label = { Text("Reps") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

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
                    enabled = reps.isNotBlank() && weight.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Set")
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Previous Sets",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(sets) { set ->
                SetItemCard(
                    set = set, onDelete = { viewModel.deleteSet(set) })
            }
        }
    }
}

@Composable
fun SetItemCard(
    set: WorkoutSet, onDelete: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${set.reps} reps × ${set.weight} kg",
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = onDelete, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Text("Delete")
            }
        }
    }
}

