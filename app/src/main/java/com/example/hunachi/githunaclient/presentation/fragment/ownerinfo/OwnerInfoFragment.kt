package com.example.hunachi.githunaclient.presentation.fragment.ownerinfo

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf

import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.FragmentOwnerInfoBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.example.hunachi.githunaclient.util.extension.observerOnChanged
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class OwnerInfoFragment : BaseFragment() {
    
    private val userName: String? by lazy { arguments?.getString(USERNAME_PARAM) }
    private lateinit var viewModel: OwnerInfoViewModel
    private lateinit var binding: FragmentOwnerInfoBinding
    private lateinit var userInfoFragment: UserInfoFragment
    private lateinit var navigator: Navigator
    private lateinit var loadingDialog: AlertDialog
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (userName == null) errorToast()
        userInfoFragment = with(userName).instance<UserInfoFragment>().value
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        navigator = with(activity).instance<Navigator>().value
        navigator.replaceFragment(R.id.user_info_container, userInfoFragment)
    }
    
    override fun onStart() {
        super.onStart()
        setupDialog()
    }
    
    private fun setupViewModel() {
        viewModel = with(userName).instance<OwnerInfoViewModel>().value
        viewModel.apply {
            loading.observerOnChanged(this@OwnerInfoFragment, Observer {
                if (it == null) return@Observer
                loadingCallback(it)
            })
            error.observerOnChanged(this@OwnerInfoFragment, Observer {
                if (it == null || it == false) return@Observer
                errorToast()
            })
        }
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        setViewModel(viewModel)
    }
    
    private fun setupDialog() {
        loadingDialog = with(activity as Context).instance<LoadingDialogAdapter>().value
                .onCreateDialog()
    }
    
    private fun loadingCallback(show: Boolean) {
        if (!show && loadingDialog.isShowing) loadingDialog.dismiss()
        else if (show && !loadingDialog.isShowing) loadingDialog.show()
    }
    
    companion object {
        const val USERNAME_PARAM = "userName"
        fun newInstance(userName: String) = OwnerInfoFragment().apply {
            arguments = bundleOf(USERNAME_PARAM to userName)
        }
    }
}
