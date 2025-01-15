package com.example.codingchallenge.data.mapper

import com.example.codingchallenge.core.utils.Utils
import com.example.codingchallenge.data.data_source.network.dto.Result
import com.example.codingchallenge.domain.model.Address
import com.example.codingchallenge.domain.model.Name
import com.example.codingchallenge.domain.model.ProfilePicture
import com.example.codingchallenge.domain.model.UserDetails
import com.example.codingchallenge.domain.model.UserModel


object UserMapper {
    fun Result.toUserModel(): UserModel {
        val name = this.name
        val location = this.location
        val locationStreet = this.location.street
        return UserModel(
            id = this.id?.value.toString() ,
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

    fun Result.toUserDetails() : UserDetails {
        return UserDetails(
            user = this.toUserModel(),
            email = this.email,
            age = this.dob.age,
            phoneNumber = this.phone,
            registeredDate = Utils.formatDate(this.registered.date),
            username = this.login.username
        )
    }



}