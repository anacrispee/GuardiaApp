package com.example.guardia.features.feature_home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.guardia.R
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.domain.utils.getDayAndMonthNameAndYear
import com.example.guardia.domain.utils.toServiceDate
import com.example.guardia.ui.app_theme.AppTheme
import com.example.guardia.ui.components.shimmer_effect.shimmerBrush
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
            HomeViewAction.GetDomesticViolenceArticles
        )
        action(
            HomeViewAction.GetDomesticViolenceStories
        )
    }

    if (viewState.isLoading) {
        LoadingScreen()
    } else {
        ContentScreen(
            viewState = viewState,
            action = action,
            listVerticalFilters = listVerticalFilters
        )
    }
}

@Composable
private fun ContentScreen(
    viewState: HomeViewState,
    action: (HomeViewAction) -> Unit,
    listVerticalFilters: List<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        InputSearch(
            searchInputValue = viewState.searchInputValue,
            action = action
        )
        Filters(
            listVerticalFilters = listVerticalFilters
        )
        ArticlesArea(
            domesticViolencePopularArticles = viewState.domesticViolencePopularArticles.orEmpty(),
            domesticViolenceStories = viewState.domesticViolenceStories.orEmpty()
        )
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
private fun ArticlesArea(
    domesticViolencePopularArticles: List<DomesticViolenceArticleResponse>,
    domesticViolenceStories: List<DomesticViolenceArticleResponse>
) {
    Text(
        text = stringResource(id = R.string.home_popular_articles),
        style = AppTheme.typography.titleBold.title_lg_bold,
        color = AppTheme.colors.primary.dark_grey,
        modifier = Modifier
            .padding(bottom = 16.dp)
    )
    LazyRow {
        items(domesticViolencePopularArticles) { article ->
            ArticleCard(
                article = article
            )
        }
    }
    Text(
        text = stringResource(id = R.string.home_personal_stories),
        style = AppTheme.typography.titleBold.title_lg_bold,
        color = AppTheme.colors.primary.dark_grey,
        modifier = Modifier
            .padding(vertical = 16.dp)
    )
    LazyRow {
        items(domesticViolenceStories) { article ->
            ArticleCard(
                article = article
            )
        }
    }
}

@Composable
private fun ArticleCard(
    article: DomesticViolenceArticleResponse,
) {
    var showShimmer by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(end = 24.dp)
            .background(
                color = AppTheme.colors.secondary.lighter,
                shape = RoundedCornerShape(14.dp)
            )
            .width(320.dp)
            .height(280.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .background(
                    shimmerBrush(
                        targetValue = 1300f,
                        showShimmer = showShimmer
                    )
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                .padding(bottom = 8.dp)
                .width(160.dp)
                .height(170.dp),
            onLoading = {
                showShimmer = true
            },
            onSuccess = {
                showShimmer = false
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            model = article.urlToImage?.ifEmpty { R.drawable.image_unavailable_image },
            error = painterResource(id = R.drawable.image_unavailable_image)
        )
        Text(
            text = article.title.orEmpty(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.bodyBold.body_small,
            color = AppTheme.colors.primary.dark_grey,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.publishedAt?.toServiceDate()?.getDayAndMonthNameAndYear().orEmpty(),
                style = AppTheme.typography.bodySemiBold.body_tiny,
                color = AppTheme.colors.primary.light_grey,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                textAlign = TextAlign.Start
            )
            Text(
                text = stringResource(id = R.string.home_see_more_news_card),
                style = AppTheme.typography.bodySemiBold.body_tiny,
                color = AppTheme.colors.primary.dark_pink,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = AppTheme.colors.primary.dark_pink
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
    searchInputValue: String,
    action: (HomeViewAction) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(50.dp)
            ),
        value = searchInputValue,
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
    ContentScreen(
        viewState = HomeViewState(),
        action = {},
        listVerticalFilters = listVerticalFilters()
    )
}