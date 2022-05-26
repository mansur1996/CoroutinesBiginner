package com.example.coroutinesbiginner.retrofit

import com.example.coroutinesbiginner.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id : Int) : User

    @GET("users")
    suspend fun getUsers(): List<User>

}