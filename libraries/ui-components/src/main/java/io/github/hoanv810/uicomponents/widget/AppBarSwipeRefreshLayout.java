package io.github.hoanv810.uicomponents.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.appbar.AppBarLayout;
import io.github.hoanv810.ui_components.R;

/**
 * Minor extension to {@link SwipeRefreshLayout} that is appbar-aware, only enabling when appbar
 * has been fully expanded.
 * This class assumes activity layout contains an {@link AppBarLayout} with {@link R.id#appbar}
 */
public class AppBarSwipeRefreshLayout extends SwipeRefreshLayout implements AppBarLayout.OnOffsetChangedListener {
    private AppBarLayout mAppBar;

    public AppBarSwipeRefreshLayout(Context context) {
        super(context);
    }

    public AppBarSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getContext() instanceof Activity) {
            mAppBar = ((Activity) getContext()).findViewById(R.id.appbar);
            if (mAppBar != null) {
                mAppBar.addOnOffsetChangedListener(this);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mAppBar != null) {
            mAppBar.removeOnOffsetChangedListener(this);
            mAppBar = null;
        }
        super.onDetachedFromWindow();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        this.setEnabled(i == 0);
    }
}
