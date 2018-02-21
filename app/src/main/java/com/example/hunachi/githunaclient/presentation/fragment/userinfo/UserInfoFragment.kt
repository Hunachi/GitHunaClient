package com.example.hunachi.githunaclient.presentation.fragment.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentUserInfoBinding

import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.*


class UserInfoFragment : BaseFragment() {
    
    private lateinit var binding: FragmentUserInfoBinding
    private val viewModel: UserInfoViewModel by with(this).instance()
    private val userName: String? by lazy { arguments?.getString(ARG_PARAM) }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        setupViewModel()
        return binding.root
    }
    
    private fun setupViewModel() {
        setViewModel(viewModel)
        binding.viewModel = viewModel
        viewModel.setUp(userName?: throw IllegalStateException("userName is null."))
    }
    
    companion object {
        private const val ARG_PARAM = "userName"
        fun newInstance(userName: String): UserInfoFragment =
                UserInfoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM, userName)
                    }
                }
    }
}
