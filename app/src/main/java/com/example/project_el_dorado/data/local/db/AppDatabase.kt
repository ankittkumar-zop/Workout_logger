package com.example.project_el_dorado.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project_el_dorado.data.local.entity.Exercise
import com.example.project_el_dorado.data.local.entity.WorkoutSet

@Database(
    entities = [Exercise::class, WorkoutSet::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSetDao(): WorkoutSetDao

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "workout_DB"
                ).build().also { INSTANCE = it }
            }
    }
}