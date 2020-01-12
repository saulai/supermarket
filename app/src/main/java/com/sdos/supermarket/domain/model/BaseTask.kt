package com.sdos.supermarket.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "base_task")
data class BaseTask(@PrimaryKey val code: String, val description: String){
    override fun toString() = description
}