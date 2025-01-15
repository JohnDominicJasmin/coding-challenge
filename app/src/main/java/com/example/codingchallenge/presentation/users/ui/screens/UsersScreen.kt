package com.example.codingchallenge.presentation.users.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.codingchallenge.R
import com.example.codingchallenge.core.di.Constants.DEFAULT_NUMBER_OF_USERS
import com.example.codingchallenge.domain.model.Address
import com.example.codingchallenge.domain.model.Name
import com.example.codingchallenge.domain.model.ProfilePicture
import com.example.codingchallenge.domain.model.UserModel
import com.example.codingchallenge.presentation.users.ui.UserEvent
import com.example.codingchallenge.presentation.users.ui.state.UserState
import com.example.codingchallenge.presentation.users.ui.components.UserInputField
import com.example.codingchallenge.presentation.users.ui.components.UserList
import com.example.codingchallenge.presentation.users.ui.state.UserUiState
import com.example.codingchallenge.presentation.users.viewmodel.UsersViewModel
import com.example.codingchallenge.ui.navigation.Screens
import com.example.codingchallenge.ui.navigation.navigateScreen

@Composable
fun UsersScreen(viewModel: UsersViewModel = hiltViewModel(), navController: NavController) {
    val userScreenState by viewModel.userState.collectAsState(initial = UserUiState())

    var numberOfUsers  by rememberSaveable { mutableStateOf<Int>(DEFAULT_NUMBER_OF_USERS) }
    UsersScreenContent(
        userScreenState = userScreenState,
        numberOfUsers = numberOfUsers,
        setNumberOfUsers = { numberOfUsers = it }, getUsers = {
            viewModel.onEvent(event  = UserEvent.GetUsers(
                page = 1,
                numberOfResult = numberOfUsers
            ))
        }, onClickUser = { user ->
            navController.navigateScreen(Screens.UserDetails.passArguments(
                id = user.id,
                numberOfResult = numberOfUsers
            ))
        })
}


@Composable
fun UsersScreenContent(
    userScreenState: UserUiState,
    numberOfUsers: Int?,
    setNumberOfUsers: (Int) -> Unit, getUsers: () -> Unit, onClickUser: (UserModel) -> Unit) {
    val inputPlaceholder = stringResource(id = R.string.how_many_users_do_you_need)
    val getUsersText = stringResource(id = R.string.get_users)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                ,
                horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
            ) {

            UserInputField(
                modifier = Modifier.weight(1f),
                value = numberOfUsers,
                inputPlaceholder = inputPlaceholder,
                onValueChange = setNumberOfUsers,
                onDone = getUsers)

            Button(
                onClick = getUsers,
                modifier = Modifier.weight(0.45f),
                shape = RoundedCornerShape(10.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.dodger_blue),
                    contentColor = Color.White
                )
            ) {
                Text(text = getUsersText)
            }
        }
        when (userScreenState.userState) {
            is UserState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            is UserState.Success -> {
                val users = (userScreenState.userState as UserState.Success).data
                if(users.isEmpty()){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No users available",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )

                    }
                }else{
                    UserList(users = users, onClickUser = onClickUser)

                }
            }

            is UserState.Failure -> {
                val exception = (userScreenState.userState as UserState.Failure).exception
                Text(
                    text = "Error: ${exception.message}",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewUsersScreen() {
    var textState  by remember { mutableStateOf<Int?>(null) }

    val mockUserState = UserState.Success(
        data = listOf(
            UserModel(
                name = Name(
                    firstName = "John",
                    lastName = "Doe",
                    title = "Mr"
                ),
                address = Address(
                    streetNumber = "12",
                    streetName = "Main St",
                    city = "New York",
                    state = "NY",
                    postcode = "10001"
                ),
                profilePicture = ProfilePicture("https://media.istockphoto.com/id/1186079153/photo/hell-stay-until-he-has-it-all-figured-out.jpg?s=612x612&w=0&k=20&c=OK2bPjppdTpV9JPMmAua9gNMQnY_PObdkzmKWFj8Pmg=")),
        )
    )

    UsersScreenContent(userScreenState = UserUiState(), numberOfUsers = 0, setNumberOfUsers = {
        textState = it
    }, getUsers = {

    }, onClickUser = {})
}

