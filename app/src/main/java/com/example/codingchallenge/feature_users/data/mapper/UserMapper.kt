package com.example.codingchallenge.feature_users.data.mapper

import com.example.codingchallenge.feature_users.data.data_source.network.dto.ResultDto
import com.example.codingchallenge.feature_users.domain.model.Address
import com.example.codingchallenge.feature_users.data.data_source.network.dto.Name as NameDto
import com.example.codingchallenge.feature_users.domain.model.Name
import com.example.codingchallenge.feature_users.domain.model.ProfilePicture
import com.example.codingchallenge.feature_users.domain.model.UserModel

object UserMapper {
    fun ResultDto.toUserModel(): UserModel {
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
            profilePicture = ProfilePicture("")
        )
    }



}