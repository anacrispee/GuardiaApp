package com.example.guardia.features.authentication.create_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guardia.R
import com.example.guardia.ui.app_theme.AppTheme
import com.example.guardia.ui.uikit.components.ErrorDisclaimer
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateAccountScreen(
    navController: NavHostController,
    viewModel: CreateAccountViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        TextFieldComponent(
            value = viewState.name,
            onValueChange = { newValue ->
                action(CreateAccountViewAction.UpdateNameInput(value = newValue))
            },
            label = R.string.my_profile_card_name
        )
        Spacer(modifier = Modifier.padding(16.dp))
        TextFieldComponent(
            value = viewState.email,
            onValueChange = { newValue ->
                action(CreateAccountViewAction.UpdateEmailInput(value = newValue))
            },
            label = R.string.my_profile_card_email
        )
        Spacer(modifier = Modifier.padding(24.dp))
        TextFieldComponent(
            value = viewState.password,
            onValueChange = { newValue ->
                action(CreateAccountViewAction.UpdatePasswordInput(value = newValue))
            },
            label = R.string.my_profile_card_password,
            isPassword = true,
            isPasswordHide = viewState.isPasswordHide,
            showPassword = {
                action(CreateAccountViewAction.ShowPassword)
            }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        TextFieldComponent(
            value = viewState.confirmPassword,
            onValueChange = { newValue ->
                action(CreateAccountViewAction.UpdatePasswordConfirmationInput(value = newValue))
            },
            label = R.string.create_account_confirm_password,
            isPassword = true,
            isPasswordHide = viewState.isPasswordConfirmationHide,
            showPassword = {
                action(CreateAccountViewAction.ShowPasswordConfirmation)
            },
            enabled = viewState.password.isNotBlank()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        viewState.createAccountError?.let {
            ErrorDisclaimer(
                text = it.message ?: stringResource(R.string.connection_error_title)
            )
        }
        if (viewState.passwordsMatch == false) {
            Spacer(modifier = Modifier.padding(16.dp))
            ErrorDisclaimer(
                text = stringResource(R.string.create_account_wrong_passwords)
            )
        }
        Spacer(modifier = Modifier.weight(0.7f))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                action(CreateAccountViewAction.CreateAccount(navController))
            },
            enabled = viewState.name.isNotBlank() &&
                    viewState.email.isNotBlank() &&
                    viewState.password.isNotBlank() &&
                    viewState.confirmPassword.isNotBlank() &&
                    viewState.passwordsMatch == true
        ) {
            Text(text = stringResource(id = R.string.create_account_button_create))
        }
    }
}

@Composable
private fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: Int,
    isPassword: Boolean = false,
    isPasswordHide: Boolean = false,
    showPassword: () -> Unit = {},
    enabled: Boolean = true
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = stringResource(id = label))
        },
        maxLines = 1,
        singleLine = true,
        visualTransformation = if (isPassword && isPasswordHide) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = { showPassword.invoke() }
                ) {
                    Icon(
                        painter = if (isPasswordHide) painterResource(id = R.drawable.ic_line_visibility_off)
                        else painterResource(id = R.drawable.ic_line_visibility),
                        contentDescription = null,
                        tint = AppTheme.colors.primary.dark_pink
                    )
                }
            }
        },
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Composable
private fun CreateAccountScreenPreview() {
    CreateAccountScreen(
        navController = rememberNavController()
    )
}