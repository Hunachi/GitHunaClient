package com.example.hunachi.githunaclient.presentation.main.profile

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventFragment
import com.example.hunachi.githunaclient.util.extension.convertToLowerText

/**
 * Created by hunachi on 2018/02/10.
 */
class ProfilePagerAdapter(
        private val fragmentManager: FragmentManager,
        private val followerEventFragment: FollowerEventFragment
) : FragmentPagerAdapter(fragmentManager) {
    
    override fun getItem(position: Int) =
        when (position) {
            0 -> followerEventFragment
            else -> null
        }
    
    override fun getCount() = 1
    
    override fun getPageTitle(position: Int) =
            when (position) {
                0    -> followerEventFragment.toString().convertToLowerText()
                else -> null
            }
}