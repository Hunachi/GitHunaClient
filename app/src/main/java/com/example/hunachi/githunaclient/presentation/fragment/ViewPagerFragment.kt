package com.example.hunachi.githunaclient.presentation.fragment

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.FragmentViewPagerBinding
import com.example.hunachi.githunaclient.kodein.viewPagerViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.main.profile.ProfilePagerAdapter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import com.github.salomonbrys.kodein.with

class ViewPagerFragment : BaseFragment() {
    
    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewModel: ViewpagerViewModel
    private lateinit var adapter: ProfilePagerAdapter
    private lateinit var userName: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = arguments?.getString(ARG_PARAM) ?: throw IllegalAccessException("userName is null")
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        setupView()
        setViewModel(viewModel)
        return binding.root
    }
    
    private fun setupView(){
        viewModel = instance<ViewpagerViewModel>().value
        adapter = with(Pair(childFragmentManager, userName)).instance<ProfilePagerAdapter>().value
        binding.apply {
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
        }
    }
    
    companion object {
        private const val ARG_PARAM = "userName"
        fun newInstance(userName: String): ViewPagerFragment =
                ViewPagerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM, userName)
                    }
                }
    }
}
