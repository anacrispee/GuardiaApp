package com.example.guardia.features.feature_home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.guardia.R
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.domain.utils.getDayAndMonthNameAndYear
import com.example.guardia.domain.utils.isNetworkAvailable
import com.example.guardia.domain.utils.toServiceDate
import com.example.guardia.ui.app_theme.AppTheme
import com.example.guardia.ui.uikit.components.shimmer_effect.shimmerBrush
import com.example.guardia.ui.uikit.generic_screens.GenericEmptyStateScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction
    val screenContext = LocalContext.current

    LaunchedEffect(true) {
        if (isNetworkAvailable(screenContext).not()) {
            navController.navigate("ConnectionErrorScreen/HomeScreen")
        } else {
            action(
                HomeViewAction.GetDomesticViolenceArticles
            )
            action(
                HomeViewAction.GetDomesticViolenceStories
            )
        }
        return@LaunchedEffect
    }

    if (viewState.isLoading) {
        LoadingScreen()
    } else {
        ContentScreen(
            viewState = viewState,
            action = action,
            listVerticalFilters = listVerticalFilters,
            navController = navController
        )
    }
}

@Composable
private fun ContentScreen(
    viewState: HomeViewState,
    action: (HomeViewAction) -> Unit,
    listVerticalFilters: List<SearchFiltersModel>,
    navController: NavHostController
) {
    var filterOption by remember { mutableIntStateOf(FiltersEnum.VIOLENCE.id) }
    var searchInputValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
                    HomeViewAction.SearchNewsSubject(
                        subject = it
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
        LazyRow {
            item { Spacer(modifier = Modifier.width(16.dp)) }
            items(listVerticalFilters) { filter ->
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .background(
                            color = AppTheme.colors.primary.dark_pink,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .clickable {
                            searchInputValue = ""
                            focusManager.clearFocus()
                            filterOption = filter.id
                            action(
                                HomeViewAction.FetchDataByFilterOption(
                                    id = filter.id
                                )
                            )
                        }
                ) {
                    Text(
                        text = stringResource(id = filter.name),
                        style = AppTheme.typography.bodyBold.body_tiny,
                        color = AppTheme.colors.secondary.lighter,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                            .clip(shape = RoundedCornerShape(50.dp))
                    )
                }
            }
            item { Spacer(modifier = Modifier.width(16.dp)) }
        }
        DefaultHomeArticles(
            viewState = viewState,
            filterOption = filterOption,
            navController = navController
        )
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
private fun DefaultHomeArticles(
    viewState: HomeViewState,
    filterOption: Int,
    navController: NavHostController
) {
    if (viewState.isEmptyState) {
        EmptyStateContentScreen()
    } else {
        if (viewState.hasSearched) {
            LazyRowItem(
                articlesTitle = decideArticlesSectionTitle(viewState.searchId),
                articlesList = decideArticlesSectionList(viewState.searchId, viewState),
                navController = navController
            )
        } else {
            LazyRowItem(
                articlesTitle = decideArticlesSectionTitle(filterOption),
                articlesList = decideArticlesSectionList(filterOption, viewState),
                navController = navController
            )
            if (filterOption == FiltersEnum.VIOLENCE.id) {
                Spacer(modifier = Modifier.height(24.dp))
                LazyRowItem(
                    articlesTitle = R.string.home_personal_stories,
                    articlesList = viewState.domesticViolenceStories ?: listOf(),
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun decideArticlesSectionList(
    listId: Int,
    viewState: HomeViewState
) = when (listId) {
    FiltersEnum.PSYCHOLOGICAL_ABUSE.id -> viewState.domesticPsychologicalAbuseArticles
        ?: listOf()

    FiltersEnum.HARASSMENT.id -> viewState.harassmentAgainstWomenArticles ?: listOf()
    FiltersEnum.THREAT.id -> viewState.threatAgainstWomenArticles ?: listOf()
    else -> viewState.domesticViolencePopularArticles ?: listOf()
}

@Composable
private fun decideArticlesSectionTitle(
    titleId: Int
) = when (titleId) {
    FiltersEnum.PSYCHOLOGICAL_ABUSE.id -> listVerticalFilters.first { it.id == titleId }.name
    FiltersEnum.HARASSMENT.id -> listVerticalFilters.first { it.id == titleId }.name
    FiltersEnum.THREAT.id -> listVerticalFilters.first { it.id == titleId }.name
    else -> R.string.home_popular_articles
}

@Composable
private fun EmptyStateContentScreen() {
    GenericEmptyStateScreen(
        image = R.drawable.magnifying_glass,
        title = R.string.empty_state_screen_title,
        subtitle = R.string.empty_state_screen_subtitle
    )
}

@Composable
private fun LazyRowItem(
    articlesTitle: Int,
    articlesList: List<DomesticViolenceArticleResponse>,
    navController: NavHostController
) {
    Text(
        text = stringResource(id = articlesTitle),
        style = AppTheme.typography.titleBold.title_lg,
        color = AppTheme.colors.primary.dark_grey,
        modifier = Modifier
            .padding(16.dp)
    )
    LazyRow {
        item { Spacer(modifier = Modifier.width(16.dp)) }
        items(articlesList) { article ->
            if (article.source?.sourceId != null)
                ArticleCard(
                    article = article,
                    navController = navController
                )
        }
    }
}

@Composable
private fun ArticleCard(
    article: DomesticViolenceArticleResponse,
    navController: NavHostController
) {
    var showShimmer by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(end = 16.dp)
            .background(
                color = AppTheme.colors.secondary.lighter,
                shape = RoundedCornerShape(14.dp)
            )
            .width(320.dp)
            .height(280.dp)
            .clickable {
                navController.navigate("ArticleReadingScreen/{$article}")
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .background(
                    shimmerBrush(
                        showShimmer = showShimmer
                    )
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                .padding(bottom = 4.dp)
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
                .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
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
                    .padding(top = 4.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
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

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ContentScreen(
        viewState = HomeViewState(),
        action = {},
        listVerticalFilters = listVerticalFilters,
        navController = rememberNavController()
    )
}