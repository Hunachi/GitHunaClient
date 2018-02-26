package com.example.hunachi.githunaclient.presentation.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainBinding
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.list.ListType
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsArgument
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.example.hunachi.githunaclient.util.NavigatorCallback
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainActivity : BaseActivity() {
    //TODO Duration
    
    private lateinit var viewModel: MainViewModel
    private val navigator: Navigator by with(this).instance()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val myApplication: MyApplication by lazy { application as MyApplication }
    private lateinit var ownerInfoFragment: UserInfoFragment
    private lateinit var viewPagerFragment: ViewPagerFragment
    private lateinit var timeLineFragment: ListsFragment
    private var userName: String? = null
    private var fragmentFrag: FragmentFrag? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = myApplication.userName
        /*if token don't have, let's go to login page.*/
        if (checkToken()) setupViewModel() else navigator.navigateToLogin()
    }
    
    private fun checkToken(): Boolean = myApplication.token.isNotBlank()
    
    private fun setupViewModel() {
        viewModel = instance<MainViewModel>().value
        binding.viewModel = viewModel.apply {
            user.observe(this@MainActivity, Observer { user ->
                if (user == null) return@Observer
                user.userName.let {
                    userName = it
                    myApplication.updateUserName(it)
                    setupFragmentManager()
                }
            })
            isShowingList.observe(this@MainActivity, Observer { item ->
                if (item == null) return@Observer
                navigationListener(item)
            })
        }
        binding.setLifecycleOwner(this)
        setViewModel(viewModel)
        /*if userName is uninitialized, let's get user info.*/
        if (userName.isNullOrBlank()) viewModel.setupUser(errorCallback) else setupFragmentManager()
    }
    
    private fun setupFragmentManager() {
        userName?.let {
            viewPagerFragment = with(it).instance<ViewPagerFragment>().value
            ownerInfoFragment = with(it).instance<UserInfoFragment>().value
            timeLineFragment = with(ListsArgument(it, ListType.TL)).instance<ListsFragment>().value
        }
        if (fragmentFrag == null)
            binding.navigation.also {
                it.selectedItemId = R.id.action_feed
                navigationListener(it.menu.getItem(FragmentFrag.FEED.ordinal))
            }
    }
    
    private val navigationListener: NavigatorCallback = { item ->
        when (item.itemId) {
            R.id.action_profile -> {
                binding.navigation.selectedItemId = R.id.action_profile
                navigator.replaceFragment(R.id.container, ownerInfoFragment)
                fragmentFrag = FragmentFrag.PROFILE
            }
            R.id.action_feed    -> {
                binding.navigation.selectedItemId = R.id.action_feed
                navigator.replaceFragment(R.id.container, timeLineFragment)
                fragmentFrag = FragmentFrag.FEED
            }
            R.id.action_lists   -> {
                binding.navigation.selectedItemId = R.id.action_lists
                navigator.replaceFragment(R.id.container, viewPagerFragment)
                fragmentFrag = FragmentFrag.LISTS
            }
        }
    }
    
    override val errorCallback: ErrorCallback = {
        errorToast()
    }
}