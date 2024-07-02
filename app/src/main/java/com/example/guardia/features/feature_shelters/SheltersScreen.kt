package com.example.guardia.features.feature_shelters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guardia.R
import com.example.guardia.data_remote.model.shelter.ShelterModel
import com.example.guardia.ui.app_theme.AppTheme
import com.example.guardia.ui.uikit.components.LoadingScreen
import com.example.guardia.ui.uikit.generic_screens.GenericEmptyStateScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun FindSheltersScreen(
    navController: NavHostController,
    viewModel: SheltersViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewState.isLoading) {
            LoadingScreen()
        } else {
            ContentScreen(
                viewState = viewState,
                action = action,
                navController = navController
            )
        }
    }
}

@Composable
private fun ContentScreen(
    viewState: SheltersViewState,
    action: (SheltersViewAction) -> Unit,
    navController: NavHostController
) {
    var searchInputValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(50.dp)
            ),
        value = searchInputValue,
        onValueChange = {
            searchInputValue = it
            action(
                SheltersViewAction.SearchNewShelter(
                    shelterName = it
                )
            )
        },
        label = {
            Text(
                text = stringResource(id = R.string.home_text_field)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.magnifying_glass),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
        },
        trailingIcon = {
            if (searchInputValue.isNotBlank()) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_close),
                    tint = AppTheme.colors.primary.light_grey,
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            searchInputValue = ""
                            focusManager.clearFocus()
                        }
                )
            }
        },
        textStyle = AppTheme.typography.bodySemiBold.body_medium.copy(
            color = AppTheme.colors.primary.light_grey
        ),
        colors = TextFieldDefaults
            .colors(
                unfocusedContainerColor = AppTheme.colors.secondary.lighter,
                focusedContainerColor = AppTheme.colors.secondary.lighter,
                unfocusedTextColor = AppTheme.colors.primary.light_grey,
                focusedTextColor = AppTheme.colors.primary.light_grey,
                focusedIndicatorColor = AppTheme.colors.primary.light_grey
            ),
        maxLines = 1
    )

    if (viewState.isEmptyState) {
        EmptyStateScreen()
    } else {
        Text(
            text = stringResource(
                id = R.string.shelters_next_to_you,
                viewState.shelters?.totalShelters ?: 0),
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        LazyColumn {
            items(viewState.shelters?.sheltersList ?: listOf()) { shelter ->
                ShelterCard(
                    shelter = shelter
                )
            }
        }
    }
}

@Composable
private fun ShelterCard(
    shelter: ShelterModel?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(16.dp)
            .background(
                color = AppTheme.colors.secondary.lighter,
                shape = RoundedCornerShape(14.dp)
            )
            .width(320.dp)
            .height(280.dp)
    ) {
        ShelterCardTitle(shelter)
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = AppTheme.colors.primary.lighter_grey
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            text = shelter?.addressModel?.street +
                    " - " + shelter?.addressModel?.neighborhood +
                    ", " + shelter?.addressModel?.city +
                    " - " + shelter?.addressModel?.state +
                    ", " + shelter?.addressModel?.zipCode,
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = AppTheme.colors.primary.lighter_grey
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = AppTheme.colors.primary.dark_pink
            ),
            onClick = {}
        ) {
            Text(
                text = stringResource(id = R.string.how_to_arrive)
            )
        }
    }
}

@Composable
private fun ShelterCardTitle(shelter: ShelterModel?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 4.dp),
            painter = painterResource(id = R.drawable.ic_line_tent_small),
            contentDescription = null
        )
        Text(
            text = shelter?.addressModel?.city.orEmpty() + " - " + shelter?.addressModel?.state.orEmpty(),
            style = AppTheme.typography.bodySemiBold.body_medium,
            color = AppTheme.colors.primary.dark_grey
        )
    }
}

@Composable
private fun EmptyStateScreen() {
    GenericEmptyStateScreen(
        image = R.drawable.magnifying_glass,
        title = R.string.empty_state_shelter_title,
        subtitle = R.string.empty_state_shelter_subtitle
    )
}

@Preview(showBackground = true)
@Composable
private fun SheltersScreenPreview() {
    FindSheltersScreen(
        navController = rememberNavController()
    )
}