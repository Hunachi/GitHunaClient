package com.example.hunachi.githunaclient.presentation.fragment.feeds

import android.arch.lifecycle.Observer
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.kodein.eventViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.FeedItemCallback
import com.example.hunachi.githunaclient.util.extension.customTabsIntent
import com.example.hunachi.githunaclient.util.extension.sepatateOwnerRepo
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import com.github.salomonbrys.kodein.with
import kotlinx.android.synthetic.main.fragment_follower_event.*

@Suppress("DEPRECATION")
/**
 * Created by hunachi on 2018/02/04.
 */
class FeedsFragment : BaseFragment() {
    
    private lateinit var binding: FragmentFollowerEventBinding
    private val followerEventList = mutableListOf<Feed>()
    private lateinit var feedsAdapter: FeedsAdapter
    private lateinit var viewModel: FeedsViewModel
    private lateinit var userName: String
    private lateinit var tabsIntent: CustomTabsIntent
    private lateinit var navigator: Navigator
    private val kodein = Kodein.lazy {
        extend(appKodein.invoke())
        import(eventViewModelModule)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = arguments?.getString(ARG_PARAME) ?: throw IllegalAccessException("hoge")
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
        navigator = with(activity).instance<Navigator>().value
        tabsIntent = activity.customTabsIntent()
    }
    
    private fun setUpRecycler() {
        viewModel = kodein.with(userName).instance<FeedsViewModel>().value
        setViewModel(viewModel)
        feedsAdapter = FeedsAdapter(followerEventList, itemIconCallback, itemCallback)
        binding.apply {
            viewModel = this@FeedsFragment.viewModel
            setLifecycleOwner(this@FeedsFragment)
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = feedsAdapter
            }
        }
        viewModel.apply {
            event.observe(this@FeedsFragment, Observer { event ->
                followerEventList.apply {
                    //O(N^2)is not good. 10^4 unit is a limit.
                    if (find { it == event } == null) {
                        event?.let { add(0, it) }
                        feedsAdapter.notifyItemInserted(0)
                    }
                }
            })
            refreshing.observe(this@FeedsFragment, Observer {
                swipe_refresh.isRefreshing = it ?: false
            })
            repositoryProcessor.subscribe({
                tabsIntent.launchUrl(activity, Uri.parse(it.htmlUrl))
            }, {
                it.printStackTrace()
            })
        }
    }
    
    private val itemIconCallback: FeedItemCallback = {
        navigator.navigateToMainProfile(it.actor)
    }
    
    private val itemCallback: FeedItemCallback = {
        viewModel.repository(it.repositoryName.sepatateOwnerRepo())
        //todo show dialog.
        Toast.makeText(activity, "hogekyo-", Toast.LENGTH_SHORT).show()
    }
    
    companion object {
        private const val ARG_PARAME = "userName"
        fun newInstance(userName: String): FeedsFragment =
                FeedsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAME, userName)
                    }
                }
    }
    
}