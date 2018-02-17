package com.example.hunachi.githunaclient.presentation.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.FragmentViewPagerBinding
import com.example.hunachi.githunaclient.kodein.viewPagerViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy

class ViewPagerFragment : BaseFragment() {
    
    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewModel: ViewpagerViewModel
    private lateinit var user: User
    
    private val kodein = Kodein.lazy{
        extend(appKodein.invoke())
        import(viewPagerViewModelModule)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments?.getSerializable(ARG_PARAM) as User
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        setupViewModel()
        setViewModel(viewModel)
        return binding.root
    }
    
    private fun setupViewModel(){
        viewModel = kodein.instance<ViewpagerViewModel>().value
    }
    
    companion object {
        private const val ARG_PARAM = "user"
        fun newInstance(user: User): ViewPagerFragment =
                ViewPagerFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM, user)
                    }
                }
    }
}
