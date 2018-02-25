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
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.FragmentUserInfoBinding

import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.util.GoWebCallback
import com.example.hunachi.githunaclient.util.extension.customTabsIntent
import com.github.salomonbrys.kodein.*


class UserInfoFragment : BaseFragment() {
    
    private lateinit var binding: FragmentUserInfoBinding
    private val viewModel: UserInfoViewModel by with(this).instance()
    private val userName: String? by lazy { arguments?.getString(USERNAME) }
    private lateinit var loadingDialog: AlertDialog //目がチカチカするから消し他方がいいかも．
    private lateinit var tabsIntent: CustomTabsIntent
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupDialog()
        setupViewModel()
    }
    
    private fun setupViewModel() {
        viewModel.let {
            setViewModel(it)
            binding.viewModel = it
            it.user.observe(this, Observer {
                loadingDialog.dismiss()
                when {
                    it?.bio.isNullOrBlank()  -> binding.userbioText.visibility = View.GONE
                    it?.blog.isNullOrBlank() -> binding.userurlText.visibility = View.GONE
                }
                it?.blog?.let { binding.userurlText.toMakeUnderline(it) }
            })
            tabsIntent = activity.customTabsIntent()
            it.setUp(userName ?: throw IllegalStateException("userName is null."), goWebCallback)
        }
    }
    
    private fun setupDialog() {
        loadingDialog = with(activity as Context).instance<LoadingDialogAdapter>().value
                .onCreateDialog()
        loadingDialog.show()
    }
    
    private val goWebCallback: GoWebCallback = { url ->
        tabsIntent.launchUrl(activity, Uri.parse(url))
    }
    
    companion object {
        private const val USERNAME = "userName"
        fun newInstance(userName: String): UserInfoFragment =
                UserInfoFragment().apply {
                    arguments = Bundle().apply {
                        putString(USERNAME, userName)
                    }
                }
    }
    
    private fun TextView.toMakeUnderline(text: String) {
        val spannable: Spannable = Spannable.Factory.getInstance().newSpannable(text)
        val un = UnderlineSpan()
        spannable.setSpan(un, 0, text.length, spannable.getSpanFlags(un))
        this.text = spannable
    }
}
