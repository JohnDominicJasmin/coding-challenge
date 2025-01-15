package com.example.codingchallenge.presentation.users.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.codingchallenge.R
import com.example.codingchallenge.domain.model.Address
import com.example.codingchallenge.domain.model.Name
import com.example.codingchallenge.domain.model.ProfilePicture
import com.example.codingchallenge.domain.model.UserDetails
import com.example.codingchallenge.domain.model.UserModel
import com.example.codingchallenge.presentation.users.ui.UserEvent
import com.example.codingchallenge.presentation.users.ui.state.UserState
import com.example.codingchallenge.presentation.users.viewmodel.UsersViewModel
import com.example.codingchallenge.ui.navigation.goBack
@Composable
fun ProfileScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    navController: NavController,
    id: String,
    numberOfResults: Int
) {
    val userState by viewModel.selectedUserState.collectAsState(initial = UserState.Loading)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        IconButton(
            onClick = { navController.goBack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp).zIndex(100F)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        when (userState) {
            is UserState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }

            is UserState.Success -> {
                val user = (userState as UserState.Success).data
                ProfileScreenContent(state = user)
            }

            is UserState.Failure -> {
                val exception = (userState as UserState.Failure).exception
                Text(text = exception.message ?: "Unknown error")
            }

            else -> {
                Text("No user data available")
            }
        }
    }

    LaunchedEffect(id) {
        viewModel.onEvent(event = UserEvent.GetUserById(id = id, numberOfResults = numberOfResults))
    }
}

@Composable
fun ProfileScreenContent(state: UserDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFFB0B0B0))
        ) {
            Image(
                painter = rememberAsyncImagePainter(state.user.profilePicture.photoUrl),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 50.dp)
                    .background(Color.White, CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.user.name.fullName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = state.age.toString() + " years old",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            DetailRow(
                label = stringResource(id = R.string.email_address),
                value = state.email,
                icon = R.drawable.baseline_alternate_email_24
            )
            DetailRow(
                label = stringResource(id = R.string.phone_number),
                value = state.phoneNumber,
                icon = R.drawable.baseline_phone_in_talk_24
            )
            DetailRow(
                label = stringResource(id = R.string.address),
                value = state.user.address.fullAddress,
                icon = R.drawable.baseline_location_on_24
            )
            DetailRow(
                label = stringResource(id = R.string.username),
                value = state.username,
                icon = R.drawable.baseline_person_24
            )
            DetailRow(
                label = stringResource(id = R.string.registered),
                value = state.registeredDate,
                icon = R.drawable.baseline_calendar_month_24
            )
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF8AB4F8)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(state = UserDetails(
        user = UserModel(
            id = "1",
            name = Name(
                firstName = "John",
                lastName = "Doe",
                title = "Mr"
                ),
            address = Address(
                streetNumber = "123",
                streetName = "Main Street",
                city = "Test",
                state = "Test",
                postcode = "12345",
                country = "Test"
            ),
            profilePicture = ProfilePicture(
                photoUrl = "https://randomuser.me/api/portraits/men/1.jpg"
            )
        ),
        age = 23,
        email = "johndoe@gmail.com",
        phoneNumber = "(123) 456-7890",
        username = "johndoe",
        registeredDate = "Jan 14, 2021"

    ))
}