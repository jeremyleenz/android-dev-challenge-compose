package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import java.util.*

data class Puppy(
    val name: String,
    val breed: String,
    val ageInMonths: Int,
    val color: String,
    @DrawableRes val photo: Int,
    val id: String = UUID.randomUUID().toString()
)
