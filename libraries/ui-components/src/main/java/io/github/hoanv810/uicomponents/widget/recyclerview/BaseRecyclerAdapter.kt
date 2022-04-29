package io.github.hoanv810.uicomponents.widget.recyclerview

import androidx.recyclerview.widget.RecyclerView

/**
 * @author hoanv
 * @since 2/24/21
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val items = mutableListOf<T>()

    fun addData(data: List<T>) {
        items.addAll(data)
    }

    fun setData(data: List<T>) {
        items.clear()
        items.addAll(data)
    }

    override fun getItemCount(): Int = items.size
}