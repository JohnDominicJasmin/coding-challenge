package com.example.codingchallenge.presentation.users.ui.state

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import com.example.codingchallenge.core.di.Constants.DEFAULT_NUMBER_OF_USERS
import com.example.codingchallenge.domain.model.UserModel
import kotlinx.serialization.Serializable

@Keep
@Stable
data class UserUiState(
    val userState: UserState<List<UserModel>> = UserState.Loading,
    val numberOfUsers: Int = DEFAULT_NUMBER_OF_USERS

)
