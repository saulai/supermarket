package com.sdos.supermarket.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val code: Int? = null,
    val description: String,
    val hours: Int,
    val taskType: String,
    val userCode: String,
    val completed: Boolean = false
)