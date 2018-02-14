package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.ActivityMainProfileBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainProfileActivity : BaseActivity() {
    
    private val binding: ActivityMainProfileBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainProfileBinding>(this, R.layout.activity_main_profile)
    }
    
    private lateinit var userName: String
    private lateinit var user: User
    private val adapter: ProfilePagerAdapter by with(supportFragmentManager).instance()
    private lateinit var dialog: AlertDialog
    private val navigator: Navigator by with(this).instance()
    private lateinit var viewModel: MainProfileViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO
        userName = intent.getStringExtra("userName")?: "hunachi"/*?:
                throw IllegalAccessException("userName is null")*/
        setupViewModel()
    }
    
    private fun setupViewModel(){
        dialog = LoadingDialogAdapter(this).onCreateDialog()
        dialog.show()
        viewModel = with(Pair(this as BaseActivity, userName)).instance<MainProfileViewModel>().value
        setViewModel(viewModel)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        setupProcessor()
    }
    
    private fun setupProcessor(){
        viewModel.processor.subscribe({
            user = it
        },{
            it.printStackTrace()
        },{
            setupView()
            dialog.dismiss()
        })
    }
    
    private fun setupView(){
        binding.apply {
            val pager = pager
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager)
        }
        val userInfoFragment: UserInfoFragment by with(user).instance()
        navigator.replaceFragment(userInfoFragment, binding.userInfoContainer.id)
    }
    
    
    
}
