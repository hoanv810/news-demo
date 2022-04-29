package io.github.hoanv810.dynamicfeature.newslist.activity

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.hoanv810.core.data.common.FetchMode.EVERYTHING
import io.github.hoanv810.dynamicfeature.news_list.R
import io.github.hoanv810.dynamicfeature.newslist.NewsListFragment

/**
 * @author hoanv
 * @since 2/25/21
 */
@AndroidEntryPoint
class EverythingActivity: BaseListActivity() {

    override fun getDefaultTitle(): String = getString(R.string.everything)

    override fun instantiateListFragment(): Fragment = NewsListFragment.newInstance(EVERYTHING)
}