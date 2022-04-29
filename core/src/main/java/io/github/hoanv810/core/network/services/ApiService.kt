package io.github.hoanv810.core.network.services

import com.haroldadmin.cnradapter.NetworkResponse
import io.github.hoanv810.core.BuildConfig
import io.github.hoanv810.core.data.common.SortBy
import io.github.hoanv810.core.network.response.ArticlesResult
import io.github.hoanv810.core.network.response.Error
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author hoanv
 * @since 2/23/21
 */
interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("page") page: Int = 1,
        @Query("sortBy") sortBy: String = SortBy.PUBLISHED_AT.value,
        @Query("country") country: String = BuildConfig.COUNTRY,
        @Query("category") category: String = BuildConfig.CATEGORY
        ): NetworkResponse<ArticlesResult, Error>

    @GET("everything")
    suspend fun everything(
        @Query("page") page: Int = 1,
        @Query("sortBy") sortBy: String = SortBy.PUBLISHED_AT.value,
        @Query("domains") domains: String = BuildConfig.DOMAINS
    ): NetworkResponse<ArticlesResult, Error>
}