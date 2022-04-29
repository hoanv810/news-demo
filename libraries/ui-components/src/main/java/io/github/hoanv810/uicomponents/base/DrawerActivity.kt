package io.github.hoanv810.uicomponents.base

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import io.github.hoanv810.actions.Actions
import io.github.hoanv810.core.data.common.FetchMode
import io.github.hoanv810.ui_components.R
import io.github.hoanv810.ui_components.R.string
import io.github.hoanv810.ui_components.databinding.ActivityDrawerBinding

/**
 * @author hoanv
 * @since 2/23/21
 */
abstract class DrawerActivity: ThemedActivity() {

    protected lateinit var binding: ActivityDrawerBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle

    private var pendingIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer)
        binding.lifecycleOwner = this

        drawerToggle = object : ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar, string.open_drawer,
            string.close_drawer
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                pendingIntent?.let {
                    it.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(it)
                    pendingIntent = null
                }
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        binding.drawerLayout.addDrawerListener(drawerToggle)

        setupDrawer()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> openDrawers()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawer() {
        findViewById<View>(R.id.drawer_top_headlines).setOnClickListener {
            navigate(Actions.openTopHeadlinesIntent(this, FetchMode.TOP_HEADLINES))
        }
        findViewById<View>(R.id.drawer_everything).setOnClickListener {
            navigate(Actions.openEverythingIntent(this))
        }
    }

    private fun navigate(intent: Intent) {
        pendingIntent = intent
        closeDrawers()
    }

    private fun openDrawers() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun closeDrawers() {
        binding.drawerLayout.closeDrawers()
    }
}