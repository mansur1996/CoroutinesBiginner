package com.example.coroutinesbiginner.model

import com.example.coroutines.model.Address
import com.example.coroutines.model.Company

data class User(
	val id: Int,
	val name: String,
	val username: String,
	val company: Company,
	val website: String,
	val address: Address,
	val phone: String,
	val email: String,
)
