package io.github.hoanv810.core.domain.usecase.article

import io.github.hoanv810.core.common.Resource
import io.github.hoanv810.core.data.common.SortBy
import io.github.hoanv810.core.domain.repositories.ArticleRepository
import io.github.hoanv810.core.network.response.ArticlesResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {

    operator fun invoke(page: Int, sortBy: SortBy): Flow<Resource<ArticlesResult>> = flow {
        emit(Resource.Loading())
        emit(articleRepository.getTopHeadlines(page = page, sortBy = sortBy))
    }
}