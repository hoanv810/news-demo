package io.github.hoanv810.dynamicfeature.newslist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import io.github.hoanv810.actions.Actions
import io.github.hoanv810.core.constant.Extras
import io.github.hoanv810.core.data.common.FetchMode
import io.github.hoanv810.core.network.response.Article
import io.github.hoanv810.core.utils.EventObserver
import io.github.hoanv810.dynamicfeature.news_list.R
import io.github.hoanv810.dynamicfeature.news_list.databinding.FragmentNewsListBinding
import io.github.hoanv810.uicomponents.base.BaseFragment
import io.github.hoanv810.uicomponents.util.showSnackMessage
import io.github.hoanv810.uicomponents.widget.SpaceDecoration
import io.github.hoanv810.uicomponents.widget.recyclerview.EndlessAdapter
import io.github.hoanv810.uicomponents.widget.recyclerview.EndlessAdapterConfig

/**
 * @author hoanv
 * @since 2/23/21
 */
@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(NewsListViewModel::class.java) }
    private lateinit var endlessAdapter: EndlessAdapter<Article>
    private val articlesAdapter = NewsListAdapter { startActivity(Actions.openNewsDetailIntent(requireActivity(), it)) }

    override fun layoutResourceId(): Int = R.layout.fragment_news_list

    override fun initContentView() {
        arguments?.getSerializable(Extras.EXTRA_FILTER)?.let { viewModel.fetchMode = it as FetchMode }
        binding.swipeLayout.apply {
            setColorSchemeResources(R.color.white)
            setProgressBackgroundColorSchemeColor(ContextCompat.getColor(requireContext(), R.color.redA200))
            setOnRefreshListener {
                viewModel.resetPage()
                viewModel.getArticles()
            }
        }

        binding.rvNews.apply {
            setHasFixedSize(true)
            addItemDecoration(SpaceDecoration(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.item_space)))
        }

        endlessAdapter = EndlessAdapter(articlesAdapter) { viewModel.getArticles() }.apply {
            config = EndlessAdapterConfig(LayoutInflater.from(requireContext()).inflate(R.layout.loading,
                binding.rvNews, false))
        }

        binding.rvNews.adapter = endlessAdapter
    }

    override fun initViewModel() {
        viewModel.apply {
            loadingState.observe(viewLifecycleOwner, EventObserver {
                if (getPage() >= 1) {
                    if (it) endlessAdapter.showLoadMore() else endlessAdapter.hideLoadMore()
                } else {
                    if (it) binding.swipeLayout.isRefreshing = true
                }
                if (!it) binding.swipeLayout.isRefreshing = false
            })

            news.observe(viewLifecycleOwner, EventObserver {
                if (getPage() == 1) endlessAdapter.setData(it.articles)
                else endlessAdapter.addData(it.articles)
            })

            errorMessage.observe(viewLifecycleOwner, EventObserver {
                showSnackMessage(it)
            })

            binding.swipeLayout.isRefreshing = true
        }
    }

    companion object {
        fun newInstance(fetchMode: FetchMode): Fragment {
            val args = Bundle().apply {
                putSerializable(Extras.EXTRA_FILTER, fetchMode)
            }
            return NewsListFragment().apply {
                arguments = args
            }
        }
    }
}