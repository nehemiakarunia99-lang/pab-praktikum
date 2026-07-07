package com.nehem.pabweek3.data

import kotlinx.serialization.Serializable

@Serializable
data class TodoItem(
    val id: Int,
    val title: String,
    val isDone: Boolean = false
)
