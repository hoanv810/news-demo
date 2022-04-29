package io.github.hoanv810.core.domain.repositories

import io.github.hoanv810.core.common.Resource
import io.github.hoanv810.core.data.common.SortBy
import io.github.hoanv810.core.network.response.ArticlesResult

interface ArticleRepository {

    suspend fun getTopHeadlines(page: Int, sortBy: SortBy): Resource<ArticlesResult>

    suspend fun getEverything(page: Int, sortBy: SortBy): Resource<ArticlesResult>
}
