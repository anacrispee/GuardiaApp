package com.example.guardia.features.feature_my_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.guardia.R
import com.example.guardia.ui.app_theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyProfileScreen(
    navController: NavHostController,
    viewModel: MyProfileViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction

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
        Header()
        MyProfileCard()
    }
}

@Composable
private fun Header() {
    AsyncImage(
        modifier = Modifier
            .padding(16.dp),
        model = model.imageProfile,
        contentDescription = null
    )
    Text(
        text = model.name,
        style = AppTheme.typography.titleBold.title_lg,
        color = AppTheme.colors.secondary.lighter
    )
}

@Composable
private fun MyProfileCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
            value = model.name
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_phone),
            value = model.phone
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_address),
            value = model.address
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_email),
            value = model.email
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_password),
            value = model.password
        )
        CardItem(
            title = stringResource(id = R.string.my_profile_card_permissions_title),
            value = stringResource(id = R.string.my_profile_card_permissions_subtitle)
        )
    }
}

@Composable
private fun CardItem(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
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
                color = AppTheme.colors.primary.dark_grey
            )
        }
        Icon(
            modifier = Modifier
                .weight(0.2f),
            painter = painterResource(id = R.drawable.ic_line_edit),
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