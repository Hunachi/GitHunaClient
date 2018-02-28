package com.example.hunachi.githunaclient.presentation.fragment.ownerinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.os.bundleOf

import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.FragmentOwnerInfoBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class OwnerInfoFragment : BaseFragment() {
    
    private val userName: String? by lazy { arguments?.getString(USERNAME_PARAM) }
    private lateinit var viewModel: OwnerInfoViewModel
    private lateinit var binding: FragmentOwnerInfoBinding
    private lateinit var userInfoFragment: UserInfoFragment
    private lateinit var navigator: Navigator
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (userName == null) errorCallback
        userInfoFragment = with(userName).instance<UserInfoFragment>().value
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerInfoBinding.inflate(inflater, container, false)
        setupViewModel()
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator = with(activity).instance<Navigator>().value
        navigator.replaceFragment(R.id.user_info_container, userInfoFragment)
    }
    
    private fun setupViewModel(){
        viewModel = instance<OwnerInfoViewModel>().value
        setViewModel(viewModel)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }
    
    override val errorCallback: ErrorCallback = {
        errorToast()
    }
    
    companion object {
        const val USERNAME_PARAM = "userName"
        fun newInstance(userName: String) = OwnerInfoFragment().apply {
            arguments = bundleOf(USERNAME_PARAM to userName)
        }
    }
}
