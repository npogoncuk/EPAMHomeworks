package com.example.baseapplicationcompomentspart2.model

import java.io.Serializable

data class Contact(
    val name: String,
    val organization: String,
    val email: String,
    val phoneNumber: String
) : Serializable
