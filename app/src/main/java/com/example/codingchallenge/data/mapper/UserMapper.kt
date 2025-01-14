package com.example.codingchallenge.data.mapper

import com.example.codingchallenge.data.data_source.network.dto.Result
import com.example.codingchallenge.domain.model.Address
import com.example.codingchallenge.domain.model.Name
import com.example.codingchallenge.domain.model.ProfilePicture
import com.example.codingchallenge.domain.model.UserModel


object UserMapper {
    fun Result.toUserModel(): UserModel {
        val name = this.name
        val location = this.location
        val locationStreet = this.location.street
        return UserModel(
            name = Name(
                firstName = name.first,
                lastName = name.last,
                title = name.title
            ),
            address = Address(
                streetNumber = locationStreet.number.toString(),
                streetName = locationStreet.name,
                city = location.city,
                state = location.state,
                postcode = location.postcode.toString(),
                country = location.country
            ),
            profilePicture = ProfilePicture(this.picture.large)
        )
    }



}