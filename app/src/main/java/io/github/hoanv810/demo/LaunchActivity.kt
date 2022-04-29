package io.github.hoanv810.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.hoanv810.actions.Actions
import io.github.hoanv810.core.data.common.FetchMode.TOP_HEADLINES

/**
 * @author hoanv
 * @since 2/23/21
 */
class LaunchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Actions.openTopHeadlinesIntent(this, TOP_HEADLINES))
        finish()
    }
}