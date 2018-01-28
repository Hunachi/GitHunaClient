package com.example.hunachi.githunaclient.presentation.oauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentOuthAuthorizationBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment

/**
 * Created by hunachi on 2018/01/28.
 */
class OauthAuthorizationFragment: BaseFragment() {
    
    
    companion object {
        fun newInstance(): OauthAuthorizationFragment{
            return OauthAuthorizationFragment().apply {  }
        }
    }
    
    private lateinit var binding: FragmentOuthAuthorizationBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOuthAuthorizationBinding
                .inflate(inflater, container, false)
        return binding.root
    }
    
}