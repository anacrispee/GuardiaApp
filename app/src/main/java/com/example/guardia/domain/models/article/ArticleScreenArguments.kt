package com.example.guardia.domain.models.article

import androidx.navigation.NavController
import com.example.guardia.domain.utils.StringUtils.ENCODER
import com.example.guardia.features.article_screen.ArticleViewModel
import java.net.URLDecoder

data class ArticleScreenArgumentsModel(
    val navController: NavController,
    val viewModel: ArticleViewModel = ArticleViewModel(),
    val article: ArticleModel
)

data class ArticleModel(
    val author: String? = null,
    val title: String? = null,
    val publishedAt: String? = null,
    val contentLink: String? = null
) {
    fun decodeArticle() = ArticleModel(
        author = URLDecoder.decode(author, ENCODER),
        title = URLDecoder.decode(title, ENCODER),
        publishedAt = URLDecoder.decode(publishedAt, ENCODER),
        contentLink = URLDecoder.decode(contentLink, ENCODER)
    )


}