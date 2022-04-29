package io.github.hoanv810.dynamicfeature.newslist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.hoanv810.core.base.BaseViewModel
import io.github.hoanv810.core.common.Resource
import io.github.hoanv810.core.data.common.FetchMode.EVERYTHING
import io.github.hoanv810.core.data.common.FetchMode.TOP_HEADLINES
import io.github.hoanv810.core.data.common.SortBy
import io.github.hoanv810.core.domain.usecase.article.GetEverythingUseCase
import io.github.hoanv810.core.domain.usecase.article.GetTopHeadlinesUseCase
import io.github.hoanv810.core.network.response.ArticlesResult
import io.github.hoanv810.core.utils.Event
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @author hoanv
 * @since 2/24/21
 */
class NewsListViewModel @ViewModelInject constructor(
    private val getEverythingUseCase: GetEverythingUseCase,
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : BaseViewModel() {

    private val _news = MutableLiveData<Event<ArticlesResult>>()
    val news: LiveData<Event<ArticlesResult>>
        get() = _news

    var fetchMode = TOP_HEADLINES

    var sortBy = SortBy.PUBLISHED_AT
        set(value) {
            if (sortBy != value) {
                field = value
                resetPage()
                getTopHeadlines()
            }
        }

    private var page = 0

    fun getArticles() {
        when(fetchMode) {
            TOP_HEADLINES -> getTopHeadlines()
            EVERYTHING -> getEverything()
        }
    }

    fun getPage(): Int = page

    fun resetPage() {
        page = 0
    }

    private fun getTopHeadlines() {
        getTopHeadlinesUseCase.invoke(++page, sortBy).onEach { res ->
            when (res) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    res.data?.let { _news.value = Event(it) }
                }
                is Resource.Error -> {
                    hideLoading()
                    raiseErrorMessage(res.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getEverything() {
        getEverythingUseCase.invoke(++page, sortBy).onEach { res ->
            when (res) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    res.data?.let { _news.value = Event(it) }
                }
                is Resource.Error -> {
                    hideLoading()
                    raiseErrorMessage(res.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}