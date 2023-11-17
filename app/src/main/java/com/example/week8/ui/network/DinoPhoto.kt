package com.example.week8.ui.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DinoPhoto(
    val name: String,
    val length: String,
    val desc: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
