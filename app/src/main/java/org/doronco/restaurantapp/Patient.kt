package org.doronco.restaurantapp

import java.time.LocalDate

data class Patient(
    val id: Int,
    val nom: String,
    val dateNaissance: String,
    val malade: Boolean,
    val score: Int
)