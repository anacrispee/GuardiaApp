package com.example.guardia.features.feature_my_profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.guardia.R
import com.example.guardia.data_remote.model.user.UserModel
import com.example.guardia.ui.app_theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyProfileScreen(
    navController: NavHostController,
    viewModel: MyProfileViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction

    BackHandler {}
    LaunchedEffect(true) {
        action(MyProfileViewAction.GetUser)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.image_default_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                user = viewState.user
            )
            MyProfileCard(
                user = viewState.user
            )
            Button(
                onClick = {
                    action(MyProfileViewAction.Logout(navController))
                }
            ) {
                Text(
                    text = stringResource(R.string.my_profile_exit_account)
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = {
                    action(MyProfileViewAction.DeleteAccount(navController))
                }
            ) {
                Text(
                    text = stringResource(R.string.my_profile_delete_account)
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
private fun Header(
    user: UserModel?
) {
    Spacer(modifier = Modifier.height(64.dp))
    AsyncImage(
        modifier = Modifier
            .size(164.dp)
            .padding(16.dp)
            .clip(
                shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = AppTheme.colors.secondary.lighter,
                shape = CircleShape
            ),
        model = user?.image ?: R.drawable.image_unavailable_image,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    Text(
        text = user?.name.orEmpty(),
        style = AppTheme.typography.titleBold.title_lg,
        color = AppTheme.colors.secondary.lighter
    )
}

@Composable
private fun MyProfileCard(
    user: UserModel?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = AppTheme.colors.secondary.lighter,
                shape = RoundedCornerShape(14.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CardTitle()
        CardItem(
            title = stringResource(id = R.string.my_profile_card_name),
            value = user?.name.orEmpty()
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_phone),
            value = user?.phone.orEmpty()
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_address),
            value = user?.address.orEmpty()
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_email),
            value = user?.email.orEmpty()
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_password),
            value = "**************"
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_permissions_title),
            value = stringResource(id = R.string.my_profile_card_permissions_subtitle),
            showIcon = false
        )
    }
}

@Composable
private fun CardItem(
    title: String,
    value: String,
    showIcon: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
        ) {
            Text(
                text = title,
                style = AppTheme.typography.titleBold.title_tiny,
                color = AppTheme.colors.primary.dark_grey
            )
            Text(
                text = value,
                style = AppTheme.typography.titleRegular.title_tiny,
                color = AppTheme.colors.primary.dark_grey,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (showIcon)
            Icon(
                modifier = Modifier
                    .weight(0.2f),
                painter = painterResource(id = R.drawable.ic_line_edit),
                tint = AppTheme.colors.primary.dark_pink,
                contentDescription = null
            )
    }
}

@Composable
private fun CardTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 8.dp),
            painter = painterResource(id = R.drawable.ic_line_profile),
            contentDescription = null,
            tint = AppTheme.colors.primary.dark_pink
        )
        Text(
            text = stringResource(id = R.string.my_profile_card_title),
            style = AppTheme.typography.titleBold.title_small,
            color = AppTheme.colors.primary.dark_grey
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyProfileScreenPreview() {
    MyProfileScreen(
        navController = rememberNavController()
    )
}