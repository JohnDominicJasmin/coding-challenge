package com.example.codingchallenge.presentation.users.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codingchallenge.domain.model.UserModel

@Composable
fun UserList(
    users: List<UserModel>,
    modifier: Modifier = Modifier,
    onClickUser: (UserModel) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(users, key = { it.id }) { user ->
            UserCard(
                photoUrl = user.profilePicture.photoUrl,
                fullName = user.name.fullName,
                address = user.address.fullAddress,
                onClick = { onClickUser(user) }
            )
        }
    }
}
