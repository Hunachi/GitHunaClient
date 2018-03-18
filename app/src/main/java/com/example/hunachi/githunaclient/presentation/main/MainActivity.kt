package com.example.hunachi.githunaclient.presentation.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainBinding
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.fragment.list.ListType
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsArgument
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsFragment
import com.example.hunachi.githunaclient.presentation.fragment.ownerinfo.OwnerInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.extension.observerOnChanged
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel
    private val navigator: Navigator by with(this).instance()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val myApplication: MyApplication by lazy { application as MyApplication }
    private var userName: String? = null
    private lateinit var ownerInfoFragment: OwnerInfoFragment
    private lateinit var viewPagerFragment: ViewPagerFragment
    private lateinit var timeLineFragment: ListsFragment
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
        fragmentFrag = intent.getSerializableExtra(FRAGMENT_FRAG_NAME) as FragmentFrag?
        binding.viewModel = viewModel.apply {
            user.observerOnChanged(this@MainActivity, Observer { user ->
                if (user == null) return@Observer
                user.userName.let {
                    userName = it
                    myApplication.updateUserName(it)
                    setupFragmentManager()
                }
            })
            navigator.observerOnChanged(this@MainActivity, Observer {
                if (it == null || (fragmentFrag == null && fragmentFrag == FragmentFrag.frag(it))) return@Observer
                replaceFragment(it)
            })
            error.observerOnChanged(this@MainActivity, Observer {
                if(it == null || it == false) return@Observer
                errorToast()
            })
        }
        binding.setLifecycleOwner(this)
        setViewModel(viewModel)
        /*if userName is uninitialized, let's get user info.*/
        if (userName.isNullOrBlank()) viewModel.setupUser() else setupFragmentManager()
    }
    
    private fun setupFragmentManager() {
        userName?.let {
            viewPagerFragment = with(it).instance<ViewPagerFragment>().value
            ownerInfoFragment = with(it).instance<OwnerInfoFragment>().value
            timeLineFragment = with(ListsArgument(it, ListType.TL)).instance<ListsFragment>().value
        }
        if (fragmentFrag == null) replaceFragment(FragmentFrag.TL.id)
        else replaceFragment(fragmentFrag?.id!!)
        fragmentFrag = null
    }
    
    private fun replaceFragment(id: Int) {
        binding.navigation.selectedItemId = id
        navigator.replaceFragment(R.id.container, fragment(id))
    }
    
    private fun fragment(id: Int): BaseFragment =
            when (id) {
                FragmentFrag.PROFILE.id -> ownerInfoFragment
                FragmentFrag.TL.id      -> timeLineFragment
                FragmentFrag.LISTS.id   -> viewPagerFragment
                else                    -> throw IllegalStateException("not exist navigation itemId")
            }
}
