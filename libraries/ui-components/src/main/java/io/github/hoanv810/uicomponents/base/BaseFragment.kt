package io.github.hoanv810.uicomponents.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @author hoanv
 * @since 2/23/21
 */
abstract class BaseFragment<B: ViewDataBinding>: Fragment(), BaseView {

    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId(), container, false)
        binding.lifecycleOwner = this

        initContentView()
        initViewModel()

        return binding.root
    }
}