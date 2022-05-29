package com.example.queerfy.model

import java.time.LocalDate

data class NewLeaseModel(
    val idProperty: Int?,
    val idUser: Int?,
    val checkIn: String?,
    val checkOut: String?,
    val totalValue: Double?,
)
