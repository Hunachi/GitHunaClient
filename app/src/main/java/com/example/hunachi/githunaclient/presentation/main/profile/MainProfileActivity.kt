package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter.ProfilePagerAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainProfileActivity : BaseActivity() {
    
    private val binding: ActivityMainProfileBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainProfileBinding>(this, R.layout.activity_main_profile)
    }
    /*userNameによってActivityを再生生することでlazyにするのとどっちがいいのか?*/
    private lateinit var userName: String
    private lateinit var adapter: ProfilePagerAdapter
    private lateinit var user: User
    private lateinit var dialog: AlertDialog
    private val navigator: Navigator by with(this).instance()
    private lateinit var viewModel: MainProfileViewModel
    private lateinit var userInfoFragment: UserInfoFragment
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = intent?.getStringExtra("userName") ?: throw IllegalAccessError("hogehoge")
        setupViewModel()
    }
    
    private fun setupViewModel() {
        dialog = LoadingDialogAdapter(this).onCreateDialog()
        dialog.show()
        viewModel = with(Pair(this as BaseActivity, userName)).instance<MainProfileViewModel>().value
        setViewModel(viewModel)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        setupProcessor()
    }
    
    private fun setupProcessor() {
        viewModel.processor.subscribe({
            user = it
        }, {
            it.printStackTrace()
        }, {
            setupView()
            dialog.dismiss()
        })
    }
    
    private fun setupView() {
        adapter = with(Pair(supportFragmentManager, user.userName)).instance<ProfilePagerAdapter>().value
        userInfoFragment = with(user.userName).instance<UserInfoFragment>().value
        binding.apply {
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
            mainProfileToolbar.let {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                mainProfileToolbar.setOnMenuItemClickListener { item ->
                    if (item.itemId == it.id) navigator.activityFinish()
                    true
                }
            }
        }
        navigator.replaceFragment(R.id.user_info_container, userInfoFragment)
    }
    
}
