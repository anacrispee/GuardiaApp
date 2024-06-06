package com.example.guardia.domain.use_case

import com.example.guardia.data_remote.model.news_api.WomansViolenceArticleResponse
import com.example.guardia.domain.core.UseCase
import com.example.guardia.domain.repository.NewsApiRemoteRepository
import kotlinx.coroutines.CoroutineScope

class GetWomansViolenceArticlesUseCase(
    scope: CoroutineScope,
    private val repository: NewsApiRemoteRepository
) : UseCase<List<WomansViolenceArticleResponse>, Unit?>(scope) {

    override fun run(params: Unit?) =
        repository.getWomansViolenceArticles()
}