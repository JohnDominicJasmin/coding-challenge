package com.example.codingchallenge.presentation.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.core.di.Constants.DEFAULT_NUMBER_OF_USERS
import com.example.codingchallenge.domain.model.UserDetails
import com.example.codingchallenge.domain.model.UserModel
import com.example.codingchallenge.domain.repository.UserRepository
import com.example.codingchallenge.presentation.users.ui.UserEvent
import com.example.codingchallenge.presentation.users.ui.state.UserState
import com.example.codingchallenge.presentation.users.ui.state.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userState = MutableSharedFlow<UserUiState>(replay = 1)
    val userState: SharedFlow<UserUiState> = _userState.asSharedFlow()

    private val _selectedUserState = MutableSharedFlow<UserState<UserDetails>?>(replay = 1)
    val selectedUserState: SharedFlow<UserState<UserDetails>?> = _selectedUserState.asSharedFlow()

    init {
        getUsers(numberOfResult = DEFAULT_NUMBER_OF_USERS)
    }

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.GetUsers -> {
                getUsers(numberOfResult = event.numberOfResult)
            }

            is UserEvent.GetUserById -> {
                getUserById( numberOfResults = event.numberOfResults, id = event.id)
            }
        }
    }

    private fun getUsers(numberOfResult: Int) {
        viewModelScope.launch {
            _userState.emit(UserUiState(userState = UserState.loading()))

            userRepository.getUsers(numberOfResult).collect { result ->
                when {
                    result.isSuccess -> {
                        val users = result.getOrNull()
                        if (users != null) {
                            _userState.emit(UserUiState(userState = UserState.Success(users)))
                        } else {
                            _userState.emit(UserUiState(userState = UserState.Failure(Exception("No users found"))))
                        }
                    }
                    result.isFailure -> {
                        val exception = result.exceptionOrNull()
                        _userState.emit(UserUiState(userState = UserState.Failure(exception ?: Exception("Unknown error"))))

                    }
                    else -> {
                        _userState.emit(UserUiState(userState = UserState.loading()))

                    }
                }
            }
        }
    }

    private fun getUserById(numberOfResults: Int, id: String) {
        viewModelScope.launch {
            _selectedUserState.emit(UserState.Loading)

            userRepository.getUserById(numberOfResults = numberOfResults, id = id).collect { result ->
                when {
                    result.isSuccess -> {
                        val user = result.getOrNull()
                        if (user != null) {
                            _selectedUserState.emit(UserState.Success(data = user))
                        } else {
                            _selectedUserState.emit(UserState.Failure(Exception("User not found")))
                        }
                    }
                    result.isFailure -> {
                        _selectedUserState.emit(UserState.Failure(result.exceptionOrNull() ?: Exception("Unknown error")))
                    }
                    else -> {
                        _selectedUserState.emit(UserState.Loading)
                    }
                }
            }
        }
    }
}
