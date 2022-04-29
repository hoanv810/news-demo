package io.github.hoanv810.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.hoanv810.core.utils.Event

/**
 * @author hoanv
 * @since 10/7/20
 */
open class BaseViewModel : ViewModel() {

    private val _loadingState = MutableLiveData<Event<Boolean>>()
    val loadingState: LiveData<Event<Boolean>>
        get() = _loadingState

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    protected fun showLoading() {
        _loadingState.value = Event(true)
    }

    protected fun hideLoading() {
        _loadingState.value = Event(false)
    }

    protected fun raiseErrorMessage(message: String?) {
        message?.let { _errorMessage.postValue(Event(message)) }
    }
}