package com.eddy.healiostest.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindData()
        initListeners()
    }

    protected abstract fun initViews()
    protected abstract fun bindData()
    protected open fun initListeners() = Unit

}