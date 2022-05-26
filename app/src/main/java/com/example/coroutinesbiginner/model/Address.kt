package com.example.coroutines.model

data class Address(
	val zipcode: String,
	val geo: Geo,
	val suite: String,
	val city: String,
	val street: String
)
