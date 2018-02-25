package com.example.hunachi.githunaclient.presentation.fragment.list

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
import com.example.hunachi.githunaclient.data.api.responce.ChildUser
import com.example.hunachi.githunaclient.data.api.responce.Gist
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.Feed
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.FeedsAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.follow.UserAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.gist.GistAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.repository.RepositoryAdapter
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ListType
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.*
import com.example.hunachi.githunaclient.util.extension.customTabsIntent
import com.example.hunachi.githunaclient.util.extension.sepatateOwnerRepo
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/02/04.
 */
class ListsFragment : BaseFragment() {
    
    private lateinit var binding: FragmentFollowerEventBinding
    private val list = mutableListOf<BaseItem>()
    private lateinit var feedsAdapter: FeedsAdapter
    private lateinit var userAdapter: UserAdapter
    private lateinit var gistAdapter: GistAdapter
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var viewModel: ListsViewModel
    private lateinit var tabsIntent: CustomTabsIntent
    private lateinit var navigator: Navigator
    private lateinit var loadingDialog: AlertDialog
    private val listsArgument: ListsArgument by lazy {
        arguments?.getSerializable(ARG_KEY) as ListsArgument
    }
    
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
    
    override fun onStart() {
        super.onStart()
        viewModel.updateList(true, loadingCallback)
    }
    
    private fun setUpRecycler() {
        setUpAdapter()
        binding.apply {
            viewModel = this@ListsFragment.viewModel
            setLifecycleOwner(this@ListsFragment)
            list.layoutManager = LinearLayoutManager(context)
        }
    }
    
    private fun setUpAdapter() {
        when (listsArgument.listsType) {
            ListType.FEEDS      -> feedsAdapter = FeedsAdapter(list, itemIconCallback, itemCallback)
            ListType.FOLLOWER,
            ListType.FOLLOWING  -> userAdapter = UserAdapter(list, itemCallback)
            ListType.GIST       -> gistAdapter = GistAdapter(list, itemCallback)
            ListType.STARED,
            ListType.WATCH,
            ListType.REPOSITORY -> repositoryAdapter = RepositoryAdapter(list, itemCallback)
        }
        binding.list.adapter = when (listsArgument.listsType) {
            ListType.FEEDS      -> feedsAdapter
            ListType.FOLLOWER,
            ListType.FOLLOWING  -> userAdapter
            ListType.GIST       -> gistAdapter
            ListType.STARED,
            ListType.WATCH,
            ListType.REPOSITORY -> repositoryAdapter
        }
    }
    
    /*once*/
    private fun setUpViewModel() {
        viewModel = with(listsArgument).instance<ListsViewModel>().value
        setViewModel(viewModel)
        viewModel.apply {
            feeds.observe(this@ListsFragment, Observer { feeds ->
                feeds?.filterNot { list.contains(it) }
                        ?.forEach {
                            list.add(0, it)
                            feedsAdapter.notifyItemInserted(0)
                        }
            })
            users.observe(this@ListsFragment, Observer { followers ->
                followers?.filterNot { list.contains(it) }
                        ?.forEach {
                            list.add(0, it)
                            userAdapter.notifyItemInserted(0)
                        }
            })
            gists.observe(this@ListsFragment, Observer { gists ->
                gists?.filterNot { list.contains(it) }
                        ?.forEach {
                            list.add(0, it)
                            gistAdapter.notifyItemInserted(0)
                        }
            })
            repositories.observe(this@ListsFragment, Observer { repositories ->
                repositories?.filterNot { list.contains(it) }
                        ?.forEach {
                            list.add(0, it)
                            repositoryAdapter.notifyItemInserted(0)
                        }
            })
        }
    }
    
    private fun setupDialog() {
        loadingDialog = with(activity as Context).instance<LoadingDialogAdapter>().value
                .onCreateDialog()
    }
    
    private val itemIconCallback: ItemCallback = {
        when (it) {
            is Feed -> navigator.navigateToMainProfile(it.actor)
        }
    }
    
    private val itemCallback: ItemCallback = {
        when (it) {
            is Feed       -> viewModel.repository(it.repositoryName.sepatateOwnerRepo(), goWebCallback)
            is ChildUser  -> navigator.navigateToMainProfile(it.userName)
            is Gist       -> goWebCallback(it.html_url)
            is Repository -> goWebCallback(it.htmlUrl)
        }
    }
    
    private val goWebCallback: GoWebCallback = { url: String ->
        loadingDialog.dismiss()
        tabsIntent.launchUrl(activity, Uri.parse(url))
    }
    
    private val loadingCallback: LoadingCallback = {
        binding.swiperefresh.isRefreshing = it
    }
    
    companion object {
        private const val ARG_KEY = "listType"
        fun newInstance(listsArgument: ListsArgument): ListsFragment =
                ListsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_KEY, listsArgument)
                    }
                }
    }
}