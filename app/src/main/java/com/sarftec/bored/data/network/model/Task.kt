package com.sarftec.bored.data.network.model

import kotlinx.serialization.Serializable

@Serializable
class Task(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)