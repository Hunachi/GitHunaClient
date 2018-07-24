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
import androidx.core.os.bundleOf
import com.example.hunachi.githunaclient.data.api.responce.ChildUser
import com.example.hunachi.githunaclient.data.api.responce.Gist
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.Feed
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.FeedAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.TimeLineAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.follow.UserAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.gist.GistAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.repository.RepositoryAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.presentation.main.FragmentFrag
import com.example.hunachi.githunaclient.util.*
import com.example.hunachi.githunaclient.util.extension.customTabsIntent
import com.example.hunachi.githunaclient.util.extension.observerOnChanged
import com.example.hunachi.githunaclient.util.extension.separateOwnerRepo
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/02/04.
 */
class ListsFragment : BaseFragment() {
    
    private lateinit var binding: FragmentFollowerEventBinding
    private lateinit var timeLineAdapter: TimeLineAdapter
    private lateinit var userAdapter: UserAdapter
    private lateinit var gistAdapter: GistAdapter
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var viewModel: ListsViewModel
    private lateinit var tabsIntent: CustomTabsIntent
    private lateinit var navigator: Navigator
    private lateinit var loadingDialog: AlertDialog
    private val listsArgument: ListsArgument by lazy {
        arguments?.getSerializable(LISTTIPE_PARAM) as ListsArgument
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = with(listsArgument).instance<ListsViewModel>().value
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
        //viewModel.init(loadingCallback, onError)
        if (viewModel.list.size <= 0) {
            loadingDialog(true)
            viewModel.updateList(true)
        }
    }
    
    /*once*/
    private fun setUpViewModel() {
        setViewModel(viewModel)
        viewModel.apply {
            onSetUp(this@ListsFragment.lunchWeb)
            listSize.observe(this@ListsFragment, Observer { listSize ->
                if (listSize == null || listSize == 0) {
                    loadingDialog(false)
                    return@Observer
                }
                if (listsArgument.listsType == ListType.FOLLOWERS || listsArgument.listsType == ListType.FOLLOWING)
                    adapter.notifyDataSetChanged()
                else adapter.notifyItemRangeInserted(0, listSize)
            })
            loading.observe(this@ListsFragment, Observer {
                if (it == null) return@Observer
                loadingDialog(it)
            })
            error.observerOnChanged(this@ListsFragment, Observer {
                if (it == null) return@Observer
                onError()
            })
        }
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
            ListType.FEED         -> feedAdapter = FeedAdapter(viewModel.list, itemCallback)
            ListType.TL           -> timeLineAdapter =
                    TimeLineAdapter(viewModel.list, itemIconCallback, itemCallback)
            ListType.FOLLOWERS,
            ListType.FOLLOWING    -> userAdapter = UserAdapter(viewModel.list, itemCallback)
            ListType.GISTS        -> gistAdapter = GistAdapter(viewModel.list, itemCallback)
            ListType.STARED,
            ListType.WATCH,
            ListType.REPOSITORIES -> repositoryAdapter =
                    RepositoryAdapter(viewModel.list, itemCallback)
        }
        binding.list.adapter = adapter
    }
    
    private val adapter by lazy {
        when (listsArgument.listsType) {
            ListType.FEED         -> feedAdapter
            ListType.TL           -> timeLineAdapter
            ListType.FOLLOWERS,
            ListType.FOLLOWING    -> userAdapter
            ListType.GISTS        -> gistAdapter
            ListType.STARED,
            ListType.WATCH,
            ListType.REPOSITORIES -> repositoryAdapter
        }
    }
    
    private fun setupDialog() {
        loadingDialog = with(activity as Context).instance<LoadingDialogAdapter>().value
            .onCreateDialog()
    }
    
    private val itemIconCallback: ItemCallback = {
        when (it) {
            is Feed -> if (isNotOwner(it.actorUserName)) navigator.navigateToMainProfile(it.actorUserName)
        }
    }
    
    private val itemCallback: ItemCallback = {
        when (it) {
            is Feed       -> viewModel.repository(it.repositoryName.separateOwnerRepo())
            is ChildUser  -> if (isNotOwner(it.userName)) navigator.navigateToMainProfile(it.userName)
            is Gist       -> lunchWeb(it.html_url)
            is Repository -> lunchWeb(it.htmlUrl)
        }
    }
    
    private fun isNotOwner(userName: String): Boolean {
        if ((activity.application as MyApplication).userName == userName) {
            navigator.navigateToMain(FragmentFrag.PROFILE)
            return false
        }
        return true
    }
    
    private val lunchWeb: GoWebCallback = { url ->
        tabsIntent.launchUrl(activity, Uri.parse(url))
    }
    
    private fun loadingDialog(show: Boolean) {
        binding.swiperefresh.isRefreshing = show
    }
    
    private fun onError() {
        loadingDialog(false)
        errorToast()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
    
    companion object {
        private const val LISTTIPE_PARAM = "listType"
        fun newInstance(listsArgument: ListsArgument): ListsFragment =
            ListsFragment().apply {
                arguments = bundleOf(LISTTIPE_PARAM to listsArgument)
            }
    }
}