package com.example.guardia.features.article_reading_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.guardia.R
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
    val decodedTitle = URLDecoder.decode(title, "UTF-8")
    val decodedAuthor = URLDecoder.decode(author, "UTF-8")
    val decodedPublishedAt = URLDecoder.decode(publishedAt, "UTF-8")
    val decodedContentLink = URLDecoder.decode(contentLink, "UTF-8")

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
                .padding(16.dp)
        ) {
            Text(text = decodedTitle)
            Text(text = decodedAuthor)
            Text(text = decodedPublishedAt)
            Text(text = decodedContentLink)
        }
    }
}