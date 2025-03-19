package com.example.guardia.features.article_screen

import android.graphics.Bitmap
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.example.guardia.R
import com.example.guardia.di.NavGraphConstants.CONNECTION_ERROR_SCREEN
import com.example.guardia.domain.models.article.ArticleModel
import com.example.guardia.domain.models.article.ArticleScreenArgumentsModel
import com.example.guardia.domain.utils.getDayAndMonthNameAndYear
import com.example.guardia.domain.utils.isNetworkAvailable
import com.example.guardia.domain.utils.toServiceDate
import com.example.guardia.ui.app_theme.AppTheme
import com.example.guardia.ui.uikit.components.CustomTopBar
import com.example.guardia.ui.uikit.components.LoadingScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleScreen(
    model: ArticleScreenArgumentsModel
) {
    val viewState = model.viewModel.viewState
    val action = model.viewModel::dispatcherViewAction
    val screenContext = LocalContext.current
    val lifecycleScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        if (isNetworkAvailable(screenContext).not()) {
            model.navController.navigate(CONNECTION_ERROR_SCREEN)
        }
        return@LaunchedEffect
    }

    Column {
        CustomTopBar(
            title = R.string.article_reading_toolbar,
            showNavigationButton = true,
            onNavigationBackClick = {
                lifecycleScope.launch {
                    action(ArticleViewAction.UpdateIsLoading(false))
                    delay(300)
                    model.navController.popBackStack()
                }
            }
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            HeaderArea(
                decodedTitle = model.article.decodeArticle().title.orEmpty(),
                decodedAuthor = model.article.decodeArticle().author.orEmpty(),
                decodedPublishedAt = model.article.decodeArticle().publishedAt.orEmpty()
            )
            WebViewArea(
                action = action,
                contentLink = model.article.decodeArticle().contentLink.orEmpty(),
                isLoading = viewState.isLoading
            )
        }
    }
}

@Composable
private fun HeaderArea(
    decodedTitle: String,
    decodedAuthor: String,
    decodedPublishedAt: String
) {
    Text(        modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp),
        text = decodedTitle,
        style = AppTheme.typography.titleBold.title_xxx_lg,
        color = AppTheme.colors.primary.dark_grey
    )
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = stringResource(
            id = R.string.article_reading_author_description,
            decodedAuthor,
            decodedPublishedAt.toServiceDate().getDayAndMonthNameAndYear()
        ),
        style = AppTheme.typography.bodyMedium.body_medium,
        color = AppTheme.colors.primary.light_grey
    )
}

@Composable
private fun WebViewArea(
    action: (ArticleViewAction) -> Unit,
    contentLink: String,
    isLoading: Boolean
) {
    if (isLoading) {
        LoadingScreen()
        return
    }

    AndroidView(
        factory = {
            WebView(it).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return true
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        action(ArticleViewAction.UpdateIsLoading(true))
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        action(ArticleViewAction.UpdateIsLoading(false))
                    }
                }

                webChromeClient = object : WebChromeClient() {

                    override fun onShowFileChooser(
                        webView: WebView?,
                        filePathCallback: ValueCallback<Array<Uri>>?,
                        fileChooserParams: FileChooserParams?,
                    ): Boolean {
                        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
                    }
                }

                settings.loadWithOverviewMode = true
                settings.mediaPlaybackRequiresUserGesture = false
                settings.setSupportMultipleWindows(true)
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.domStorageEnabled = true
                settings.allowContentAccess = true
            }
        },
        update = { webView ->
            webView.loadUrl(contentLink)
        },
        onRelease = { webView ->
            webView.stopLoading()
            webView.clearHistory()
            webView.removeAllViews()
            webView.destroy()
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun ArticleReadingScreenPreview() {
    ArticleScreen(
        model = ArticleScreenArgumentsModel(
            viewModel = koinViewModel(),
            navController = rememberNavController(),
            article = ArticleModel(
                author = "Lohane Vekanandre",
                title = "As Aventuras de Timtim",
                publishedAt = "17/03/2000",
                contentLink = "url"
            )
        )
    )
}