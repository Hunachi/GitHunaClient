package com.hunachi.hunachi.githunaclient.presentation.fragment.ownerinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import com.hunachi.hunachi.githunaclient.R
import com.hunachi.hunachi.githunaclient.data.repository.GithubApiRepository
import com.hunachi.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.hunachi.hunachi.githunaclient.util.rx.SchedulerProvider
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
    private val imageIdProcessor: PublishProcessor<Int> = PublishProcessor.create()
    private val loadingProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    private val errorProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    val commits: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(commitCountProcessor)
    var commitsUnitText = if (commits.value == 1) "commit" else "commits"
    val imageId: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(imageIdProcessor)
    val loading: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(loadingProcessor)
    val error: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(errorProcessor)
    /*it's not absolutely necessary*/
    private lateinit var localDate: String
    private val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'hh:mm:ss'Z'")
    
    override fun onCreate() {
        super.onCreate()
        localDate = LocalDate.now().minusDays(8).toString(formatter)
    }
    
    override fun onResume() {
        super.onResume()
        errorProcessor.onNext(false)
        if (imageId.value == null) imageIdProcessor.onNext(loadingImageId)
    }
    
    private fun updateCommitCounter() {
        if(loading.value == true) return
        var newContributionCount = 0
        loadingProcessor.onNext(true)
        githubApiRepository.repositories(ownerName)
                .flatMap { it.toObservable() }
                .filter { it.updatedAt > localDate }
                .flatMap { githubApiRepository.repoCommitStatus(ownerName, it.name) }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    newContributionCount += it.owner?.last() ?: 0
                }, {
                    errorProcessor.onNext(true)
                    it.printStackTrace()
                }, {
                    loadingProcessor.onNext(false)
                    commitCountProcessor.onNext(newContributionCount)
                    if (newContributionCount >= 10) imageIdProcessor.onNext(greatImageId)
                    else imageIdProcessor.onNext(notgoodImageId)
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