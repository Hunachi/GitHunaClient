package com.example.hunachi.githunaclient.presentation

import android.app.FragmentManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventFragment
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainProfileActivity : BaseActivity() {
    
    //TODO merge ProfileLayout
    val binding: ActivityMainProfileBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainProfileBinding>(this, R.layout.activity_main_profile)
    }
    val adapter: ProfilePagerAdapter by with(supportFragmentManager).instance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            val pager = pager
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
        }
    }
    
    /*class ProfilePagerAdapter(
            private val fragmentManager: android.support.v4.app.FragmentManager,
            private val followerEventFragment: FollowerEventFragment,
            private val userInfoFragment: UserInfoFragment
    ) : FragmentPagerAdapter(fragmentManager) {
        
        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> {
                    //fragmentManager.beginTransaction().add(R.id.pager,followerEventFragment).commit()
                    return FollowerEventFragment.newInstance()
                }
            }
            return userInfoFragment
        }
        
        override fun getCount() = 2
        
        override fun getPageTitle(position: Int) =
                when (position) {
                    0    -> "hoge"
                    else -> "nya"
                }
    }*/
    
}
