package com.example.hunachi.githunaclient.presentation.fragment.userinfo

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AlertDialog
import android.text.Spannable
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import com.example.hunachi.githunaclient.databinding.FragmentUserInfoBinding

import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.example.hunachi.githunaclient.util.extension.customTabsIntent
import com.example.hunachi.githunaclient.util.extension.observerOnChanged
import com.github.salomonbrys.kodein.*


class UserInfoFragment : BaseFragment() {
    
    private lateinit var binding: FragmentUserInfoBinding
    private lateinit var viewModel: UserInfoViewModel
    private val userName: String? by lazy { arguments?.getString(USERNAME_PARAM) }
    private lateinit var loadingDialog: AlertDialog //目がチカチカするから消し他方がいいかも．
    private lateinit var tabsIntent: CustomTabsIntent
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = with(userName).instance<UserInfoViewModel>().value
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        container?.removeAllViews()
        if (userName == null) errorToast()
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        setupDialog()
        binding.setLifecycleOwner(this)
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tabsIntent = activity.customTabsIntent()
        setupViewModel()
    }
    
    private fun setupViewModel() {
        viewModel.also {
            setViewModel(it)
            binding.viewModel = it
        }.apply {
                    user.observe(this@UserInfoFragment, Observer {
                        if (it == null) return@Observer
                        when {
                            it.bio.isNullOrBlank()  -> binding.userbioText.visibility = View.GONE
                            it.blog.isNullOrBlank() -> binding.userurlText.visibility = View.GONE
                        }
                        it.blog?.let { binding.userurlText.toMakeUnderline(it) }
                    })
                    launchWeb.observerOnChanged(this@UserInfoFragment, Observer {
                        if (it == null) return@Observer
                        launchWebCallback(it)
                    })
                    loading.observerOnChanged(this@UserInfoFragment, Observer {
                        if (it == null) return@Observer
                        loadingCallback(it)
                    })
                    error.observe(this@UserInfoFragment, Observer {
                        if (it == null || it == false) return@Observer
                        errorToast()
                    })
                }
    }
    
    private fun setupDialog() {
        loadingDialog = with(activity as Context).instance<LoadingDialogAdapter>().value
                .onCreateDialog()
        loadingDialog.show()
    }
    
    private fun launchWebCallback(url: String) {
        tabsIntent.launchUrl(activity, Uri.parse(url))
    }
    
    private fun loadingCallback(show: Boolean) {
        if (show) loadingDialog.show()
        else if (loadingDialog.isShowing) loadingDialog.dismiss()
    }
    
    companion object {
        private const val USERNAME_PARAM = "userName"
        fun newInstance(userName: String): UserInfoFragment =
                UserInfoFragment().apply {
                    arguments = bundleOf(USERNAME_PARAM to userName)
                }
    }
    
    private fun TextView.toMakeUnderline(text: String) {
        val spannable: Spannable = Spannable.Factory.getInstance().newSpannable(text)
        val un = UnderlineSpan()
        spannable.setSpan(un, 0, text.length, spannable.getSpanFlags(un))
        this.text = spannable
    }
}
