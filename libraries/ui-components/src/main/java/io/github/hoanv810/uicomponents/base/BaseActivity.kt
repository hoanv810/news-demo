package io.github.hoanv810.uicomponents.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author hoanv
 * @since 2/24/21
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), BaseView {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId())
        binding.lifecycleOwner = this

        initContentView()
        initViewModel()
    }
}

