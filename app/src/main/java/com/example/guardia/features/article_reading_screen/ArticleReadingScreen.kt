package com.example.guardia.features.article_reading_screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guardia.R
import com.example.guardia.di.NavGraphConstants.CONNECTION_ERROR_SCREEN
import com.example.guardia.domain.utils.getDayAndMonthNameAndYear
import com.example.guardia.domain.utils.isNetworkAvailable
import com.example.guardia.domain.utils.toServiceDate
import com.example.guardia.ui.app_theme.AppTheme
import com.example.guardia.ui.uikit.components.CustomTopBar
import java.net.URLDecoder

@Composable
fun ArticleReadingScreen(
    navController: NavHostController,
    title: String,
    author: String,
    publishedAt: String,
    contentLink: String
) {
    val screenContext = LocalContext.current
    val decodedTitle = URLDecoder.decode(title, "UTF-8")
    val decodedAuthor = URLDecoder.decode(author, "UTF-8")
    val decodedPublishedAt = URLDecoder.decode(publishedAt, "UTF-8")
    val decodedContentLink = URLDecoder.decode(contentLink, "UTF-8")

    LaunchedEffect(true) {
        if (isNetworkAvailable(screenContext).not()) {
            navController.navigate(CONNECTION_ERROR_SCREEN)
        }
        return@LaunchedEffect
    }

    Column {
        CustomTopBar(
            title = R.string.article_reading_toolbar,
            showNavigationButton = true,
            onNavigationBackClick = {
                navController.popBackStack()
            }
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            HeaderArea(
                decodedTitle = decodedTitle,
                decodedAuthor = decodedAuthor,
                decodedPublishedAt = decodedPublishedAt
            )
            WebViewArea(
                contentLink = decodedContentLink
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
    contentLink: String
) {
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
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun ArticleReadingScreenPreview() {
    ArticleReadingScreen(
        navController = rememberNavController(),
        title = "O Resgate do Soldado Ryan",
        author = "Robert Rodat",
        publishedAt = "20 mar. 1998",
        contentLink = "batatinhafrita123.com.br"
    )
}