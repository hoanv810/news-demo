package io.github.hoanv810.dynamicfeature.newslist.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.hoanv810.core.data.common.SortBy.POPULARITY
import io.github.hoanv810.core.data.common.SortBy.PUBLISHED_AT
import io.github.hoanv810.core.data.common.SortBy.RELEVANCY
import io.github.hoanv810.dynamicfeature.newslist.NewsListViewModel
import io.github.hoanv810.ui_components.R
import io.github.hoanv810.uicomponents.base.DrawerActivity

/**
 * @author hoanv
 * @since 2/23/21
 */
val LIST_FRAGMENT_TAG: String = BaseListActivity::class.java.name + ".LIST_FRAGMENT_TAG"
abstract class BaseListActivity: DrawerActivity() {

    private val viewModel: NewsListViewModel by lazy {
        ViewModelProvider(this).get(NewsListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getDefaultTitle()
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.list, instantiateListFragment(), LIST_FRAGMENT_TAG)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sort, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when(viewModel.sortBy) {
            PUBLISHED_AT -> menu?.findItem(R.id.menu_sort_publishedAt)?.isChecked = true
            RELEVANCY -> menu?.findItem(R.id.menu_sort_relevancy)?.isChecked = true
            POPULARITY -> menu?.findItem(R.id.menu_sort_popularity)?.isChecked = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.menu_sort_group) {
            item.isChecked = true
            viewModel.sortBy = when(item.itemId) {
                R.id.menu_sort_publishedAt -> PUBLISHED_AT
                R.id.menu_sort_popularity -> POPULARITY
                else -> RELEVANCY
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Gets default title to be displayed in list-only layout
     * @return displayed title
     */
    protected abstract fun getDefaultTitle(): String

    /**
     * Creates list fragment to host list data
     * @return list fragment
     */
    protected abstract fun instantiateListFragment(): Fragment
}