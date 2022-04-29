package io.github.hoanv810.core.network.response

import com.google.gson.annotations.SerializedName

/**
 * @author hoanv
 * @since 2/24/21
 */
data class ArticlesResult(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)