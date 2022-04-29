package io.github.hoanv810.dynamicfeature.newslist.activity

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.hoanv810.core.data.common.FetchMode.TOP_HEADLINES
import io.github.hoanv810.dynamicfeature.news_list.R
import io.github.hoanv810.dynamicfeature.newslist.NewsListFragment

/**
 * @author hoanv
 * @since 2/23/21
 */
@AndroidEntryPoint
class TopHeadlinesActivity: BaseListActivity() {

    override fun getDefaultTitle(): String = getString(R.string.top_headlines)

    override fun instantiateListFragment(): Fragment = NewsListFragment.newInstance(TOP_HEADLINES)
}