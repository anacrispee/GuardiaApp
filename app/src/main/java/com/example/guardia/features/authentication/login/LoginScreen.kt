package com.example.guardia.features.authentication.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guardia.R
import com.example.guardia.di.NavGraphConstants.CREATE_ACCOUNT_SCREEN
import com.example.guardia.di.NavGraphConstants.HOME_SCREEN
import com.example.guardia.ui.app_theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction
    val context = LocalContext.current

    if (viewState.isLoading.not() && viewState.hasLogged && viewState.error == null) {
        Toast.makeText(
            context,
            context.getString(R.string.login_screen_success_message),
            Toast.LENGTH_SHORT
        ).show()
        navController.navigate(HOME_SCREEN)
    }

    LoginScreenContent(
        viewState = viewState,
        action = action,
        navController = navController
    )
}

@Composable
private fun LoginScreenContent(
    viewState: LoginViewState,
    action: (LoginViewAction) -> Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewState.emailInput,
            onValueChange = { newValue ->
                action(LoginViewAction.UpdateEmailInput(value = newValue))
            },
            label = {
                Text(text = stringResource(id = R.string.login_screen_email))
            },
            maxLines = 1,
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewState.passwordInput,
            onValueChange = { newValue ->
                action(LoginViewAction.UpdatePasswordInput(value = newValue))
            },
            label = {
                Text(text = stringResource(id = R.string.login_screen_password))
            },
            visualTransformation = if (viewState.isPasswordHide) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(
                    onClick = {
                        action(LoginViewAction.ShowPassword)
                    }
                ) {
                    Icon(
                        painter = if (viewState.isPasswordHide) painterResource(id = R.drawable.ic_line_visibility_off)
                        else painterResource(id = R.drawable.ic_line_visibility),
                        contentDescription = null,
                        tint = AppTheme.colors.primary.dark_pink
                    )
                }
            },
            maxLines = 1,
            singleLine = true
        )
        viewState.loginError?.let {
            Spacer(modifier = Modifier.padding(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Red,
                        shape = MaterialTheme.shapes.small
                    )
                    .background(
                        color = Color.Red.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.small
                    )
            ) {
                Text(
                    text = stringResource(R.string.login_screen_error_message),
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                action(LoginViewAction.Login)
            },
            enabled = viewState.emailInput.isNotBlank() &&
                    viewState.passwordInput.isNotBlank()
        ) {
            Text(text = stringResource(id = R.string.login_screen_login))
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                navController.navigate(CREATE_ACCOUNT_SCREEN)
            }
        ) {
            Text(text = stringResource(id = R.string.create_account_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController()
    )
}