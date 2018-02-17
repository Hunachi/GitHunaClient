package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.fragment.profile.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainProfileActivity : BaseActivity() {
    
    private val binding: ActivityMainProfileBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainProfileBinding>(this, R.layout.activity_main_profile)
    }
    
    private lateinit var userName: String
    private lateinit var user: User
    private lateinit var adapter: ProfilePagerAdapter
    private lateinit var dialog: AlertDialog
    private val navigator: Navigator by with(this).instance()
    private lateinit var viewModel: MainProfileViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = intent.getStringExtra("userName")
        if(userName.isBlank()){}
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
        adapter = with(Pair(supportFragmentManager, user)).instance<ProfilePagerAdapter>().value
        binding.apply {
            val pager = pager
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
            
            mainProfileToolbar.let {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                mainProfileToolbar?.setOnMenuItemClickListener { item ->
                    if (item.itemId == it?.id) navigator.activityFinish()
                    true
                }
            }
        }
        val userInfoFragment: UserInfoFragment by with(user).instance()
        replaceFragment(R.id.user_info_container, userInfoFragment)
    }
    
}
