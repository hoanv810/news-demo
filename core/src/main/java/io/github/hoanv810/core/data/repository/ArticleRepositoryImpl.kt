package io.github.hoanv810.core.data.repository

import android.content.Context
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.hoanv810.core.common.Resource
import io.github.hoanv810.core.data.common.SortBy
import io.github.hoanv810.core.domain.repositories.ArticleRepository
import io.github.hoanv810.core.network.response.ArticlesResult
import io.github.hoanv810.core.network.services.ApiService
import io.github.hoanv810.core.utils.internetError
import io.github.hoanv810.core.utils.unknownError
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val api: ApiService
) : ArticleRepository {

    override suspend fun getTopHeadlines(page: Int, sortBy: SortBy): Resource<ArticlesResult> {
        return when (val res = api.getTopHeadlines(page, sortBy.value)) {
            is NetworkResponse.Success -> Resource.Success(data = res.body)
            is NetworkResponse.ServerError -> Resource.Error(message = res.body?.message ?: context.unknownError)
            is NetworkResponse.NetworkError -> Resource.Error(message = context.internetError)
            is NetworkResponse.UnknownError -> Resource.Error(message = context.unknownError)
        }
    }

    override suspend fun getEverything(page: Int, sortBy: SortBy): Resource<ArticlesResult> {
        return when (val res = api.everything(page, sortBy.value)) {
            is NetworkResponse.Success -> Resource.Success(data = res.body)
            is NetworkResponse.ServerError -> Resource.Error(message = res.body?.message ?: context.unknownError)
            is NetworkResponse.NetworkError -> Resource.Error(message = context.internetError)
            is NetworkResponse.UnknownError -> Resource.Error(message = context.unknownError)
        }
    }
}