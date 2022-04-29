package io.github.hoanv810.uicomponents.base

import androidx.annotation.LayoutRes

/**
 * @author hoanv
 * @since 2/23/21
 */
interface BaseView {

    @LayoutRes
    fun layoutResourceId(): Int

    fun initContentView()

    fun initViewModel()
}