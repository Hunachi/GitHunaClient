package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainProfileActivity : BaseActivity() {
    
    //TODO merge ProfileLayout
    val binding: ActivityMainProfileBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainProfileBinding>(this, R.layout.activity_main_profile)
    }
    val adapter: ProfilePagerAdapter by with(supportFragmentManager).instance()
    val viewModel: MainProfileViewModel by with(User()).instance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            val pager = pager
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
        }
    }
    
    fun setupView(){
        setViewModel(viewModel)
    }
    
}
