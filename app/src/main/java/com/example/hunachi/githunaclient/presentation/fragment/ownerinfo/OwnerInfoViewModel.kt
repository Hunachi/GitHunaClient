package com.example.hunachi.githunaclient.presentation.fragment.ownerinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/25.
 */
class OwnerInfoViewModel(

) : BaseFragmentViewModel() {
    var contributionCount = 0
    var contribution = contributionCount.toString()
    var contributionUnitText = if(contributionCount == 1)"contribution" else "contributions"
    private val imageIcProcessor: PublishProcessor<Int> = PublishProcessor.create()
    var imageId: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(imageIcProcessor)
    
    fun noClickReload(){
        imageIcProcessor.onNext(R.drawable.contribute_notgood_image)
    }
}