package com.sarftec.bored.domain.model

data class Task(
    val activity: String,
    val link: String,
    val type: String,
    val participants: Int,
    val price: Double
)