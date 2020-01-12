package com.sdos.supermarket.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "user")
data class User(@PrimaryKey val userId: String, val firstName: String, val userType: String, val capabilities: List<String>)