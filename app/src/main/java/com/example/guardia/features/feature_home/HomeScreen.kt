package com.example.guardia.features.feature_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guardia.R
import com.example.guardia.ui.app_theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction
    val listVerticalFilters = listVerticalFilters()

    LaunchedEffect(key1 = true) {
        action(
            HomeViewAction.GetWomanViolenceArticles
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        InputSearch(
            viewState = viewState,
            action = action
        )
        Filters(
            listVerticalFilters = listVerticalFilters
        )
        Text(
            text = stringResource(id = R.string.home_popular_articles),
            style = AppTheme.typography.titleBold.title_lg_bold,
            color = AppTheme.colors.primary.dark_grey,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
        Text(
            text = stringResource(id = R.string.home_personal_stories),
            style = AppTheme.typography.titleBold.title_lg_bold,
            color = AppTheme.colors.primary.dark_grey,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
    }
}

@Composable
private fun Filters(
    listVerticalFilters: List<Int>
) {
    LazyRow {
        items(listVerticalFilters) { filter ->
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .background(
                        color = AppTheme.colors.primary.dark_pink,
                        shape = RoundedCornerShape(50.dp)
                    )
            ) {
                Text(
                    text = stringResource(id = filter),
                    style = AppTheme.typography.bodyBold.body_tiny,
                    color = AppTheme.colors.secondary.lighter,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                        .clip(shape = RoundedCornerShape(50.dp))
                )
            }
        }
    }
}

@Composable
private fun InputSearch(
    viewState: HomeViewState,
    action: (HomeViewAction) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(50.dp)
            ),
        value = viewState.searchInputValue,
        onValueChange = {
            action(
                HomeViewAction.ChangeInputValue(
                    value = it
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
            )
    )
}

private fun listVerticalFilters() = listOf(
    R.string.home_filter_violence,
    R.string.home_filter_psychological_abuse,
    R.string.home_filter_harassment,
    R.string.home_filter_threat
)

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}