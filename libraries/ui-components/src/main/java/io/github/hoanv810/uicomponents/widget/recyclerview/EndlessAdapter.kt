package io.github.hoanv810.uicomponents.widget.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.github.hoanv810.uicomponents.widget.recyclerview.EndlessScrollListener.OnLoadMoreListener
import timber.log.Timber

/**
 * @author hoanv
 * @since 2/24/21
 *
 * Adapter with endless scroll
 */
private const val VIEW_ITEM_LOAD_MORE = 0
private const val VIEW_TYPE_ITEM = VIEW_ITEM_LOAD_MORE + 1
class EndlessAdapter<T>(private val childAdapter: BaseRecyclerAdapter<T>,
    val endlessListener: (() -> Unit)? =  null) : RecyclerView.Adapter<ViewHolder>() {

    /**
     * hold reference to the LoadMore item to change its height
     */
    var loadMoreVH: LoadMoreVH? = null
    var config: EndlessAdapterConfig? = null
    var scrollListener: EndlessScrollListener = EndlessScrollListener(object : OnLoadMoreListener {
        override fun onLoadMore(page: Int, totalItemsCount: Int) {
            endlessListener?.invoke()
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == VIEW_ITEM_LOAD_MORE) {
            loadMoreVH = LoadMoreVH(config!!.loadingView)
            return loadMoreVH!!
        }

        return childAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_ITEM_LOAD_MORE) return
        childAdapter.onBindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        config?.let {
            return if (position == itemCount - 1) VIEW_ITEM_LOAD_MORE
            else VIEW_TYPE_ITEM
        }

        return VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int = childAdapter.itemCount + if (config != null) 1 else 0

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        childAdapter.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(scrollListener)
    }

    fun addData(data: List<T>) {
        childAdapter.addData(data)
        notifyDataSetChanged()
    }

    fun setData(data: List<T>) {
        resetEndless()
        childAdapter.setData(data)
        notifyDataSetChanged()
    }

    private fun resetEndless() {
        scrollListener.clear()
    }

    fun showLoadMore() {
        Timber.d("showLoadMore")
        config?.loadingView?.visibility = View.VISIBLE
    }

    /**
     * If we do not set the height of the LoadMoreVH to 0 it will take space in RecyclerView even the visibility is GONE
     */
    fun hideLoadMore() {
        Timber.d("hideLoadMore")
        config?.loadingView?.visibility = View.INVISIBLE
    }

    class LoadMoreVH(v: View): RecyclerView.ViewHolder(v)
}

const val ITEMS_PER_PAGE = 20
class EndlessAdapterConfig constructor(val loadingView: View) {
    init {
        loadingView.visibility = View.INVISIBLE
    }
}