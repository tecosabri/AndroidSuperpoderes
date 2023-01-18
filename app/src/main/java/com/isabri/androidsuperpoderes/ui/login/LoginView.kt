package com.isabri.androidsuperpoderes.ui.login

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keepcoding.androidsuperpoderes.R

@Preview
@Composable
fun LoginView() {

    val loginViewModel = hiltViewModel<LoginViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo()
        LoginForm(loginViewModel)
    }
}

@Preview
@Composable
fun AppLogo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "App icon",
            Modifier
                .clip(CircleShape)
                .border(
                    border = BorderStroke(3.dp, Color.Black),
                    shape = CircleShape
                )
                .background(Color.Green)
        )

        Text(
            text = "Android Superpoderes",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginForm(viewModel: LoginViewModel) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var email by rememberSaveable {
            mutableStateOf("hisme14@gmail.com")
        }
        var password by rememberSaveable {
            mutableStateOf("123456")
        }
        FormField(
            value = email,
            placeHolder = "Email",
            leadingIcon = Icons.Default.Email,
            onValueChange = {
                email = it
            })

        FormField(
            value = password,
            placeHolder = "Password",
            leadingIcon = Icons.Default.Password,
            onValueChange = {
                password = it
            })

        LoginButton {
            viewModel.login(email, password)
            Log.d("Login", "Login button pressed")
        }
    }
}

@Composable
fun FormField(
    value: String,
    placeHolder: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
){
    TextField(
        modifier = Modifier
            .padding(4.dp),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = leadingIcon.name)
        },
        trailingIcon = {
            trailingIcon?.let { Icon(imageVector = it, contentDescription = it.name) }
        },
        placeholder = {
            FormFieldPlaceHolder(placeHolder)
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
fun FormFieldPlaceHolder(placeholder: String) {
    Text(text = placeholder)
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .size(width = 200.dp, height = 40.dp)
    ) {
        Text(text = "Login")
    }
}