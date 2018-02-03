package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.model.Key_
import com.example.hunachi.githunaclient.util.TestSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import org.junit.Before
import org.junit.Test

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepositoryTest {
    
    
    lateinit var scheduler: SchedulerProvider
    lateinit var githubApiRepository: GithubApiRepository
    
    @Before
    fun init(){
        scheduler =  TestSchedulerProvider()
        githubApiRepository = GithubApiRepository(scheduler)
    }
    /*生まれて初めて書いたテストが通って嬉しい(((o(*ﾟ▽ﾟ*)o)))！！*/
    @Test
    fun user() {
        githubApiRepository.user(Key_.token)
                .observeOn(scheduler.io())
                .subscribe({
                    assert(it.userName == "Hunachi")
                },{
                    it.printStackTrace()
                    assert(false)
                })
    }
}