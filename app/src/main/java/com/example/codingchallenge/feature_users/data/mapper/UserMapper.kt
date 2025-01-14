package com.example.codingchallenge.feature_users.data.mapper

import android.provider.ContactsContract.Profile
import com.example.codingchallenge.feature_users.data.data_source.network.dto.Location
import com.example.codingchallenge.feature_users.data.data_source.network.dto.Picture
import com.example.codingchallenge.feature_users.domain.model.Address
import com.example.codingchallenge.feature_users.data.data_source.network.dto.Name as NameDto
import com.example.codingchallenge.feature_users.domain.model.Name
import com.example.codingchallenge.feature_users.domain.model.ProfilePicture

object UserMapper {
    fun mapUserDtoToUser(userDto: NameDto): Name {
        return Name(
            firstName = userDto.first,
            lastName = userDto.last,
            title = userDto.title
        )
    }

    fun mapLocationDtoToAddress(location: Location): Address {
        return Address(
            streetNumber = location.street.number.toString(),
            streetName = location.street.name,
            city = location.city,
            state = location.state,
            postcode = location.postcode.toString(),
            country = location.country
        )
    }

    fun pictureToProfilePicture(picture: Picture): ProfilePicture {
        return ProfilePicture(
            photoUrl = picture.large
        )
    }
}