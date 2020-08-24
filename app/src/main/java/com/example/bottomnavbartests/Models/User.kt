package com.example.bottomnavbartests.Models

data class User(val name: String,
                 val bio: String,
                 val age: String,
                val email:String,
                 val profilePicturePath: String?,
                 val registrationTokens: MutableList<String>) {
    constructor(): this("", "", "","",null, mutableListOf())
}
