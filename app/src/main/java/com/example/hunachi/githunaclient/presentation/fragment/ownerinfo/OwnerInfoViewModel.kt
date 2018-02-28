package com.example.hunachi.githunaclient.presentation.fragment.ownerinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.Observer
import android.util.Log
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.LoadingCallback
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.text.DateFormat


/**
 * Created by hunachi on 2018/02/25.
 */
class OwnerInfoViewModel(
        val scheduler: SchedulerProvider,
        val githubApiRepository: GithubApiRepository,
        val ownerName: String
) : BaseFragmentViewModel() {
    
    private val commitCountProcessor: PublishProcessor<Int> = PublishProcessor.create()
    private val commits: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(commitCountProcessor)
    var commitsString = commits.value?.toString() ?: "0"
        private set
    var commitsUnitText = if (commits.value == 1) "commits" else "commitsString"
    private val imageIcProcessor: PublishProcessor<Int> = PublishProcessor.create()
    val imageId: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(imageIcProcessor)
    private lateinit var loadingCallback: LoadingCallback
    /*it's not absolutely necessary*/
    private lateinit var localDate: String
    private val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'hh:mm:ss'Z'")
    
    override fun onCreate() {
        super.onCreate()
        localDate = LocalDate.now().minusDays(7).toString(formatter)
    }
    
    fun init(callback: LoadingCallback) {
        loadingCallback = callback
        imageIcProcessor.onNext(loadingImageId)
    }
    
    fun updateContributionCount() {
        loadingCallback(true)
        imageIcProcessor.onNext(loadingImageId)
        githubApiRepository.repositories(ownerName)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe {
                    if (it.isNotEmpty()) updateCommits(it)
                }
    }
    
    private fun updateCommits(list: List<Repository>) {
        var newContributionCount = 0
        Single.fromCallable {
            list.filter { it.updatedAt > localDate }
                    .forEach {
                        githubApiRepository.repoCommitStatus(ownerName, it.name)
                                .subscribeOn(scheduler.io())
                                .observeOn(scheduler.ui())
                                .subscribe({
                                    newContributionCount += it.owner?.last() ?: 0
                                }, {
                                    it.printStackTrace()
                                })
                    }
    }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    loadingCallback(false)
                    if (commits.value ?: 0 > 10) imageIcProcessor.onNext(greatImageId)
                    else imageIcProcessor.onNext(notgoodImageId)
                }, {
                    it.printStackTrace()
                })
        
    }
    
    fun noClickReload() {
        updateContributionCount()
    }
    
    companion object {
        const val loadingImageId = R.drawable.contribute_loading_image
        const val greatImageId = R.drawable.contribute_great_image
        const val notgoodImageId = R.drawable.contribute_notgood_image
    }
}