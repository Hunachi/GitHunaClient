package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter.ProfilePagerAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.presentation.main.FragmentFrag
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
        setupView()
    }
    
    private fun setupView() {
        viewPagerFragment = with(userName).instance<ViewPagerFragment>().value
        userInfoFragment = with(userName).instance<UserInfoFragment>().value
        adapter = with(supportFragmentManager to userName).instance<ProfilePagerAdapter>().value
        binding.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        navigator.replaceFragment(R.id.viewpager_container, viewPagerFragment)
        navigator.replaceFragment(R.id.user_info_container, userInfoFragment)
    }
    
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (it.itemId == android.R.id.home) {
                navigator.navigateToMain()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    
    override fun onDestroy() {
        super.onDestroy()
    }
}
