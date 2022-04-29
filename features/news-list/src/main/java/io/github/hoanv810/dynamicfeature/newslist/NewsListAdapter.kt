package io.github.hoanv810.dynamicfeature.newslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.hoanv810.core.network.response.Article
import io.github.hoanv810.dynamicfeature.news_list.databinding.ListItemNewsBinding
import io.github.hoanv810.uicomponents.widget.recyclerview.BaseRecyclerAdapter
import kotlinx.android.extensions.LayoutContainer

/**
 * @author hoanv
 * @since 2/24/21
 */
class NewsListAdapter(val onclick: ((article: Article) -> Unit)?) : BaseRecyclerAdapter<Article>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        return NewsVH(ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsVH).bind(items[position])
    }

    inner class NewsVH(private val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root), LayoutContainer {

        override val containerView: View
            get() = binding.root

        fun bind(article: Article) {
            binding.article = article
            binding.root.setOnClickListener { onclick?.invoke(article) }
            binding.executePendingBindings()
        }
    }
}

