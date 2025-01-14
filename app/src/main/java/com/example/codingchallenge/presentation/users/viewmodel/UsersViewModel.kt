package com.example.codingchallenge.presentation.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.core.di.Constants.DEFAULT_NUMBER_OF_USERS
import com.example.codingchallenge.domain.model.UserModel
import com.example.codingchallenge.domain.repository.UserRepository
import com.example.codingchallenge.presentation.users.ui.UserEvent
import com.example.codingchallenge.presentation.users.ui.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository

) : ViewModel() {

    private val _userState = MutableStateFlow<UserState<List<UserModel>>>(UserState.Loading)
    val userState: StateFlow<UserState<List<UserModel>>> = _userState.asStateFlow()

    init {
        getUsers(page  = 1, numberOfResult =  DEFAULT_NUMBER_OF_USERS)
    }

fun onEvent(event: UserEvent){
    when(event){
        is UserEvent.GetUsers -> {
            getUsers(page = event.page, numberOfResult = event.numberOfResult)
        }
    }
}

    private fun getUsers(page: Int, numberOfResult: Int) {
        viewModelScope.launch {

            userRepository.getUsers(page, numberOfResult).collect { result ->
                _userState.update { currentState ->
                    UserState.Loading
                    when {
                        result.isSuccess -> {
                            val users = result.getOrNull()
                            if (users != null) {
                                UserState.Success(users)
                            } else {
                                UserState.Failure(Exception("No users found"))
                            }
                        }
                        result.isFailure -> {
                            val exception = result.exceptionOrNull()
                            UserState.Failure(exception ?: Exception("Unknown error"))
                        }
                        else -> {
                            UserState.Loading
                        }
                    }
                }
            }
        }
    }}