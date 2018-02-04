package com.example.hunachi.githunaclient.presentation.event

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentUserInfoBinding

import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.appKodein


class UserInfoFragment : BaseFragment() {
    
    /*死んだ時に復帰できる?ようにlazyじゃなくてlateinitに...*/
    private lateinit var binding: FragmentUserInfoBinding
    private lateinit var viewModel: UserInfoViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = with(this).instance<UserInfoViewModel>().value
        setViewModel(viewModel)
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
    
    companion object {
        fun newInstance(): UserInfoFragment {
            val fragment = UserInfoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
