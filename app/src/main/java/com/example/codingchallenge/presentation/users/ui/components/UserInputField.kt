package com.example.codingchallenge.presentation.users.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codingchallenge.R
import com.example.codingchallenge.core.di.Constants.DEFAULT_NUMBER_OF_USERS

@Composable
fun UserInputField(
    modifier: Modifier = Modifier,
    inputPlaceholder: String,
    value: Int?,
    onValueChange: (Int) -> Unit,
    onDone: () -> Unit
) {
    var text by remember { mutableStateOf(value.toString()) }
    OutlinedTextField(
    maxLines = 1,
        value = text,
        onValueChange = { newValue ->
            if (newValue.isEmpty()) {
                text = "0"
                onValueChange(DEFAULT_NUMBER_OF_USERS)
            } else if (newValue.all { it.isDigit() }) {
                text = newValue
                onValueChange(newValue.toInt())
            }
        },
        label = { Text(text = inputPlaceholder, style = TextStyle(
            color = colorResource(id = R.color.dove_gray),
            fontSize = 14.sp

        )) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.dove_gray),
            unfocusedBorderColor = colorResource(id = R.color.mercury)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUserInputField() {
    UserInputField(
        value = 5,
        inputPlaceholder = "Enter number of users",
        onValueChange = { newText -> println("Number of users: $newText") },
        onDone = { println("Done pressed!") }
    )
}
