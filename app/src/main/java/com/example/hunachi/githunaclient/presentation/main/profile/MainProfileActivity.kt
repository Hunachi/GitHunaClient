package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainProfileActivity : BaseActivity() {
    
    //TODO merge ProfileLayout
    private val binding: ActivityMainProfileBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainProfileBinding>(this, R.layout.activity_main_profile)
    }
    private val adapter: ProfilePagerAdapter by with(supportFragmentManager).instance()
    private val userInfoFragment: UserInfoFragment by instance()
    private val navigator: Navigator by with(this as AppCompatActivity).instance()
    //val viewModel: MainProfileViewModel by with(User()).instance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }
    
    private fun setupView(){
        //setViewModel(viewModel)
        binding.apply {
            val pager = pager
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
        }
        navigator.replaceFragment(userInfoFragment, binding.userInfoContainer.id)
    }
    
}
