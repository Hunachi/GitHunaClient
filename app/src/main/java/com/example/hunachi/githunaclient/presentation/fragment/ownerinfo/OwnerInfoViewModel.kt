package com.example.hunachi.githunaclient.presentation.fragment.ownerinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.LoadingCallback
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.toObservable
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat


/**
 * Created by hunachi on 2018/02/25.
 */
class OwnerInfoViewModel(
        val scheduler: SchedulerProvider,
        val githubApiRepository: GithubApiRepository,
        val ownerName: String
) : BaseFragmentViewModel() {
    
    private val commitCountProcessor: PublishProcessor<Int> = PublishProcessor.create()
    val commits: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(commitCountProcessor)
    var commitsUnitText = if (commits.value == 1) "commit" else "commits"
    private val imageIcProcessor: PublishProcessor<Int> = PublishProcessor.create()
    val imageId: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(imageIcProcessor)
    private lateinit var loadingCallback: LoadingCallback
    /*it's not absolutely necessary*/
    private lateinit var localDate: String
    private val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'hh:mm:ss'Z'")
    
    override fun onCreate() {
        super.onCreate()
        localDate = LocalDate.now().minusDays(8).toString(formatter)
    }
    
    fun init(callback: LoadingCallback) {
        loadingCallback = callback
        imageIcProcessor.onNext(loadingImageId)
    }
    
    private fun updateCommitCounter() {
        var newContributionCount = 0
        loadingCallback(true)
        githubApiRepository.repositories(ownerName)
                .flatMap { it.toObservable() }
                .filter { it.updatedAt > localDate }
                .flatMap { githubApiRepository.repoCommitStatus(ownerName, it.name) }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    newContributionCount += it.owner?.last() ?: 0
                }, {
                    it.printStackTrace()
                }, {
                    loadingCallback(false)
                    commitCountProcessor.onNext(newContributionCount)
                    if (newContributionCount >= 10) imageIcProcessor.onNext(greatImageId)
                    else imageIcProcessor.onNext(notgoodImageId)
                })
    }
    
    fun noClickReload() {
        updateCommitCounter()
    }
    
    companion object {
        const val loadingImageId = R.drawable.contribute_loading_image
        const val greatImageId = R.drawable.contribute_great_image
        const val notgoodImageId = R.drawable.contribute_notgood_image
        const val zero = "0"
    }
}