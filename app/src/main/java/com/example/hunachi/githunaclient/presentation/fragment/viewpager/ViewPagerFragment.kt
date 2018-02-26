package com.example.hunachi.githunaclient.presentation.fragment.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.hunachi.githunaclient.databinding.FragmentViewPagerBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter.ProfilePagerAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class ViewPagerFragment : BaseFragment() {
    
    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var adapter: ProfilePagerAdapter
    private lateinit var userName: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(USERNAME_PARAM).let {
            if (it == null) errorCallback
            else userName = it
        }
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }
    
    private fun setupView() {
        adapter = with(Pair(childFragmentManager, userName)).instance<ProfilePagerAdapter>().value
        binding.apply {
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
        }
    }
    
    override val errorCallback: ErrorCallback = {
        errorToast()
    }
    
    companion object {
        private const val USERNAME_PARAM = "userName"
        fun newInstance(userName: String): ViewPagerFragment =
                ViewPagerFragment().apply {
                    arguments = Bundle().apply {
                        putString(USERNAME_PARAM, userName)
                    }
                }
    }
}
