package com.example.androiddevchallenge.model

import java.util.*

data class Puppy(
    val name: String,
    val breed: String,
    val ageInMonths: Int,
    val color: String,
    val id: String = UUID.randomUUID().toString()
)
