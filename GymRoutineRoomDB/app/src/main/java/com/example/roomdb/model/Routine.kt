package com.example.roomdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.roomdb.converter.ListConverter
import java.util.Date

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val routineId: Int,
    val dueDay: Date,
    @TypeConverters(ListConverter::class)
    val exercise: List<Exercise>
)