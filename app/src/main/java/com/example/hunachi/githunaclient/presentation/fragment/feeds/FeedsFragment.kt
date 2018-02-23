package com.example.hunachi.githunaclient.presentation.fragment.feeds

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.FeedItemCallback
import com.example.hunachi.githunaclient.util.extension.customTabsIntent
import com.example.hunachi.githunaclient.util.extension.sepatateOwnerRepo
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import kotlinx.android.synthetic.main.fragment_follower_event.*

/**
 * Created by hunachi on 2018/02/04.
 */
class FeedsFragment : BaseFragment() {
    
    private lateinit var binding: FragmentFollowerEventBinding
    private val followerEventList = mutableListOf<Feed>()
    private lateinit var feedsAdapter: FeedsAdapter
    private lateinit var viewModel: FeedsViewModel
    private val userName: String? by lazy { arguments?.getString(ARC_PARAM) ?: throw IllegalAccessException("hoge")}
    private lateinit var tabsIntent: CustomTabsIntent
    private lateinit var navigator: Navigator
    private lateinit var loadingDialog: AlertDialog
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerEventBinding.inflate(inflater, container!!, false)
        setUpRecycler()
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupDialog()
        tabsIntent = activity.customTabsIntent()
        navigator = with(activity).instance<Navigator>().value
    }
    
    private fun setUpRecycler() {
        feedsAdapter = FeedsAdapter(followerEventList, itemIconCallback, itemCallback)
        binding.apply {
            viewModel = this@FeedsFragment.viewModel
            setLifecycleOwner(this@FeedsFragment)
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = feedsAdapter
            }
        }
    }
    
    /*once*/
    private fun setUpViewModel(){
        viewModel = with(userName).instance<FeedsViewModel>().value
        setViewModel(viewModel)
        viewModel.apply {
            feeds.observe(this@FeedsFragment, Observer { event ->
                followerEventList.also { list ->
                    event?.forEach{
                        if(!list.contains(it)){
                            list.add(0, it)
                            feedsAdapter.notifyItemInserted(0)
                        }
                    }
                }
            })
            refreshing.observe(this@FeedsFragment, Observer {
                swiperefresh.isRefreshing = it ?: false
                if(it == true) loadingDialog.show()
                else loadingDialog.dismiss()
            })
            repository.observe(this@FeedsFragment, Observer {
                loadingDialog.dismiss()
                tabsIntent.launchUrl(activity, Uri.parse(it?.htmlUrl))
            })
        }
    }
    
    private fun setupDialog(){
        loadingDialog = with(activity as Context).instance<LoadingDialogAdapter>().value
                .onCreateDialog()
    }
    
    private val itemIconCallback: FeedItemCallback = {
        navigator.navigateToMainProfile(it.actor)
    }
    
    private val itemCallback: FeedItemCallback= {
        loadingDialog.show()
        viewModel.repository(it.repositoryName.sepatateOwnerRepo())
    }
    
    companion object {
        private const val ARC_PARAM = "userName"
        fun newInstance(userName: String): FeedsFragment =
                FeedsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARC_PARAM, userName)
                    }
                }
    }
    
}