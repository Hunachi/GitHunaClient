package com.example.hunachi.githunaclient.presentation.fragment.feeds

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.kodein.eventViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.FeedItemCallback
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
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
    private lateinit var user: User
    private lateinit var navigator: Navigator
    private val kodein = Kodein.lazy{
        extend(appKodein.invoke())
        import(eventViewModelModule)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments?.getSerializable("user") as User
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerEventBinding.inflate(inflater, container!!, false)
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator = with(this.activity).instance<Navigator>().value
        setUpRecycler()
    }
    
    override fun onStart() {
        super.onStart()
    }
    
    private fun setUpRecycler() {
        viewModel = kodein.with(user).instance<FeedsViewModel>().value
        setViewModel(viewModel)
        feedsAdapter = FeedsAdapter(followerEventList, itemCallback)
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
        }
    }
    
    private val itemCallback: FeedItemCallback = {
        navigator.navigateToMainProfile(it.actor)
    }
    
    companion object {
        fun newInstance(user: User): FeedsFragment =
                FeedsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("user", user)
                    }
                }
    }
    
}