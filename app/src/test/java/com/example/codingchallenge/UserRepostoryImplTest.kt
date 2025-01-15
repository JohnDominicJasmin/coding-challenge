package com.example.codingchallenge
import com.example.codingchallenge.domain.model.UserDetails
import com.example.codingchallenge.domain.model.UserModel
import com.example.codingchallenge.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class UserRepositoryTest {

    private lateinit var userRepository: FakeUserRepository

    @Before
    fun setup() {
        userRepository = FakeUserRepository()
    }

    @Test
    fun `test getUsers returns success`() = runBlocking {
        val numberOfResults = 1
        userRepository.getUsers(numberOfResults).collect { result ->
            assertTrue(result.isSuccess)
            assertEquals(1, result.getOrNull()?.size)
            val user = result.getOrNull()?.first()
            assertEquals("1", user?.id)
        }
    }

    @Test
    fun `test getUserById returns success for valid id`() = runBlocking {
        val userId = "1"
        val numberOfResults = 1
        userRepository.getUserById(numberOfResults, userId).collect { result ->
            assertTrue(result.isSuccess)
            val userDetails = result.getOrNull()
            assertEquals("John Doe", userDetails?.user?.name?.fullName)
            assertEquals("30", userDetails?.age.toString())
        }
    }

    @Test
    fun `test getUsers returns empty list when no users found`() = runBlocking {
        val fakeEmptyRepo = object : UserRepository {
            override suspend fun getUsers(numberOfResults: Int) = flow<Result<List<UserModel>>> { emit(Result.success(emptyList())) }
            override suspend fun getUserById(numberOfResults: Int, id: String) = flow<Result<UserDetails>> { emit(Result.failure(Exception("User not found"))) }
        }
        fakeEmptyRepo.getUsers(1).collect { result ->
            assertTrue(result.isSuccess)
            assertTrue(result.getOrNull().isNullOrEmpty())
        }
    }

    @Test
    fun `test getUserById returns failure for invalid id`() = runBlocking {
        val userId = "9999"
        val numberOfResults = 1
        userRepository.getUserById(numberOfResults, userId).collect { result ->
            assertTrue(result.isFailure)
            val exception = result.exceptionOrNull()
            assertEquals("User not found", exception?.message)
        }
    }

    @Test
    fun `test getUsers returns failure when repository error occurs`() = runBlocking {
        val fakeErrorRepo = object : UserRepository {
            override suspend fun getUsers(numberOfResults: Int) = flow<Result<List<UserModel>>> { emit(Result.failure(Exception("Repository error"))) }
            override suspend fun getUserById(numberOfResults: Int, id: String) = flow<Result<UserDetails>> { emit(Result.failure(Exception("User not found"))) }
        }
        fakeErrorRepo.getUsers(1).collect { result ->
            assertTrue(result.isFailure)
            val exception = result.exceptionOrNull()
            assertEquals("Repository error", exception?.message)
        }
    }

    @Test
    fun `test getUserById returns failure for empty response`() = runBlocking {
        val userId = "2"
        val numberOfResults = 1
        userRepository.getUserById(numberOfResults, userId).collect { result ->
            assertTrue(result.isFailure)
            val exception = result.exceptionOrNull()
            assertEquals("User not found", exception?.message)
        }
    }
}
