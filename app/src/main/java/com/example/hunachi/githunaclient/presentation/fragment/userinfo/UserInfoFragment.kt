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
import com.example.hunachi.githunaclient.databinding.FragmentUserInfoBinding

import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.example.hunachi.githunaclient.util.GoWebCallback
import com.example.hunachi.githunaclient.util.LoadingCallback
import com.example.hunachi.githunaclient.util.extension.customTabsIntent
import com.github.salomonbrys.kodein.*
import kotlinx.android.synthetic.main.activity_main.*


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
        if (userName == null) errorCallback
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        setupDialog()
        binding.setLifecycleOwner(this)
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
    }
    
    private fun setupViewModel() {
        viewModel.let {
            setViewModel(it)
            binding.viewModel = it
            it.user.observe(this, Observer {
                if (it == null) return@Observer
                when {
                    it.bio.isNullOrBlank()  -> binding.userbioText.visibility = View.GONE
                    it.blog.isNullOrBlank() -> binding.userurlText.visibility = View.GONE
                }
                it.blog?.let { binding.userurlText.toMakeUnderline(it) }
            })
            tabsIntent = activity.customTabsIntent()
            /*userName has already null check.*/
            it.setUp(goWebCallback, loadingCallback, errorCallback)
        }
    }
    
    private fun setupDialog() {
        loadingDialog = with(activity as Context).instance<LoadingDialogAdapter>().value
                .onCreateDialog()
    }
    
    private val goWebCallback: GoWebCallback = { url ->
        tabsIntent.launchUrl(activity, Uri.parse(url))
    }
    
    private val loadingCallback: LoadingCallback = { show ->
        if (show) loadingDialog.show()
        else if (loadingDialog.isShowing) loadingDialog.dismiss()
    }
    
    override val errorCallback: ErrorCallback = {
        errorToast()
    }
    
    companion object {
        private const val USERNAME_PARAM = "userName"
        fun newInstance(userName: String): UserInfoFragment =
                UserInfoFragment().apply {
                    arguments = Bundle().apply {
                        putString(USERNAME_PARAM, userName)
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
