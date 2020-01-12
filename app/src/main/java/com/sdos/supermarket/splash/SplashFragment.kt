package com.sdos.supermarket.splash

import android.os.Bundle
import android.util.MonthDisplayHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sdos.supermarket.BuildConfig
import com.sdos.supermarket.R
import com.sdos.supermarket.common.BaseFragment
import com.sdos.supermarket.common.navigate
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.core.inject

class SplashFragment : BaseFragment() {

    private val factory by inject<SplashViewModelFactory> ()
    private val viewModel by lazy { ViewModelProvider(this, factory)
        .get(SplashViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        viewModel.onViewReady()
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.dataBaseReady.observe(
            viewLifecycleOwner,
            Observer { isDataReady -> if (isDataReady) navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment()) })
    }

    private fun initView() {
        versionName.text = "${getString(R.string.version)} ${BuildConfig.VERSION_NAME}"
    }

}