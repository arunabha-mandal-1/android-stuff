package com.example.roomdb.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("name"), Index("age")],
    foreignKeys = [ForeignKey(entity = Gender::class, parentColumns = ["id"], childColumns = ["gender"])]
)
data class Trainee(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val gender: Int?,
    @Embedded
    val routine: Routine
)