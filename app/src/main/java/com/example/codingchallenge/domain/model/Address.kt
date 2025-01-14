package com.example.codingchallenge.domain.model

data class Address(
    val streetNumber: String = "",
    val streetName: String = "",
    val city: String = "",
    val state: String = "",
    val postcode: String = "",
    val country: String = "",
){
    val fullAddress: String
        get() = "$streetNumber $streetName, $city, $state, $postcode, $country"
}