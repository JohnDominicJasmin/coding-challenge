package com.example.codingchallenge

import com.example.codingchallenge.domain.model.Address
import com.example.codingchallenge.domain.model.Name
import com.example.codingchallenge.domain.model.ProfilePicture
import com.example.codingchallenge.domain.model.UserDetails
import com.example.codingchallenge.domain.model.UserModel
import com.example.codingchallenge.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserRepository : UserRepository {
    override suspend fun getUsers(numberOfResults: Int): Flow<Result<List<UserModel>>> {
        return flow {
            emit(Result.success(mockUserModels))  // Simulate a successful result
        }
    }

    override suspend fun getUserById(numberOfResults: Int, id: String): Flow<Result<UserDetails>> {
        return flow {
            val userDetails = mockUserDetails(id)
            if (userDetails != null) {
                emit(Result.success(userDetails))  // Simulate a successful result
            } else {
                emit(Result.failure(Exception("User not found")))  // Simulate a failure result
            }
        }
    }

    private fun mockUserDetails(id: String): UserDetails? {
        // Return mock data based on id
        return if (id == "1") {
            UserDetails(
                user = mockUserModels.first(),
                age = 30,
                email = "johndoe@example.com",
                phoneNumber = "(123) 456-7890",
                username = "johnny",
                registeredDate = "Jan 1, 2020"
            )
        } else {
            null
        }
    }

    private val mockUserModels = listOf(
        UserModel(
            id = "1",
            name = Name(firstName = "John", lastName = "Doe", title = "Mr"),
            address = Address(
                streetNumber = "123",
                streetName = "Main St",
                city = "New York",
                state = "NY",
                postcode = "10001",
                country = "USA"
            ),
            profilePicture = ProfilePicture(photoUrl = "https://example.com/photo.jpg")
        )
    )
}
