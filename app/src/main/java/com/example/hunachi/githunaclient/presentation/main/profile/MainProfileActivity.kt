package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter.ProfilePagerAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainProfileActivity : BaseActivity() {
    
    private val binding: ActivityMainProfileBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainProfileBinding>(this, R.layout.activity_main_profile)
    }
    private val userName: String? by lazy { intent?.getStringExtra("userName") }
    private lateinit var adapter: ProfilePagerAdapter
    private val navigator: Navigator by with(this).instance()
    private lateinit var viewPagerFragment: ViewPagerFragment
    private lateinit var userInfoFragment: UserInfoFragment
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (userName == (application as MyApplication).userName) navigator.activityFinish()
        else setupView()
    }
    
    private fun setupView() {
        viewPagerFragment = with(userName).instance<ViewPagerFragment>().value
        userInfoFragment = with(userName).instance<UserInfoFragment>().value
        adapter = with(Pair(supportFragmentManager, userName)).instance<ProfilePagerAdapter>().value
        binding.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        navigator.replaceFragment(R.id.viewpager_container, viewPagerFragment)
        navigator.replaceFragment(R.id.user_info_container, userInfoFragment)
    }
    
    override val errorCallback: ErrorCallback = {
        errorToast()
    }
}
