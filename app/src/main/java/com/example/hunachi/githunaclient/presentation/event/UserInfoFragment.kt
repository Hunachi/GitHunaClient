package com.example.hunachi.githunaclient.presentation.event

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentUserInfoBinding

import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with


class UserInfoFragment : BaseFragment() {
    
    private lateinit var binding: FragmentUserInfoBinding
    //private lateinit var viewModel: UserInfoViewModel
    private val viewModel: UserInfoViewModel by with(this).instance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
    
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //viewModel = with(activity).instance<UserInfoViewModel>().value
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
