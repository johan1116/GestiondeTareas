package com.unicaldas.room_database

import androidx.room.*

@Entity
data class ToDo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val user: String,
    val password:String
)
