package com.example.hunachi.githunaclient.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.FragmentUserInfoBinding

import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.*


class UserInfoFragment : BaseFragment() {
    
    /*死んだ時に復帰できる?ようにlazyじゃなくてlateinitに...*/
    private lateinit var binding: FragmentUserInfoBinding
    private lateinit var viewModel: UserInfoViewModel
    private lateinit var user: User
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        user = arguments?.getSerializable("user") as User
        setupViewModel()
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
    
    private fun setupViewModel(){
        viewModel = with(Pair(this as BaseFragment, user)).instance<UserInfoViewModel>().value
        setViewModel(viewModel)
    }
    
    companion object {
        fun newInstance(user: User): UserInfoFragment {
            val fragment = UserInfoFragment()
            val args = Bundle().apply {
                putSerializable("user", user)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
