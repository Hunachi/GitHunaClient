package com.example.hunachi.githunaclient.presentation.oauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentOuthAuthorizationBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/01/28.
 */
class OauthFragment : BaseFragment() {
    
    private val viewModel: OauthViewModel by with(this).instance()
    
    companion object {
        fun newInstance(): OauthFragment {
            return OauthFragment().apply {  }
        }
    }
    
    private lateinit var binding: FragmentOuthAuthorizationBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOuthAuthorizationBinding
                .inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        setViewModel(viewModel)
        return binding.root
    }
}