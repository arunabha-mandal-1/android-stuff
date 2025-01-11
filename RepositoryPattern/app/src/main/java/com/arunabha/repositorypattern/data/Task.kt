package com.arunabha.repositorypattern.data

import androidx.room.Entity
import androidx.room.PrimaryKey
//import kotlinx.serialization.Serializable

//@Serializable
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String? = "",
    var desc: String? = ""
)
