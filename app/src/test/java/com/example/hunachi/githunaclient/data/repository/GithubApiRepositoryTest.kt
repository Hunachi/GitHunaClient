package com.example.hunachi.githunaclient.data.repository

import android.util.Log
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.model.Key_
import com.example.hunachi.githunaclient.util.TestSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepositoryTest {
    
    
    lateinit var scheduler: SchedulerProvider
    lateinit var githubApiRepository: GithubApiRepository
    private var user: User? = null
    
    @Before
    fun init() {
        scheduler = TestSchedulerProvider()
        githubApiRepository = GithubApiRepository(scheduler, Key_.token)
    }
    
    /*生まれて初めて書いたテストが通って嬉しい(((o(*ﾟ▽ﾟ*)o)))！！*/
    private fun user() {
        githubApiRepository.user()
                .subscribe({
                    assert(it.userName == "Hunachi")
                    user = it
                    followerEvent()
                }, {
                    it.printStackTrace()
                    assert(false)
                })
    }
    
    private fun followerEvent() {
            githubApiRepository.follwerEvent(user?.userName!!, 1)
                    .subscribe({
                        assert(it.size > 10)
                    }, {
                        it.printStackTrace()
                        assert(false)
                    })
    }
    
    @Test
    fun testAll(){
        user()
    }
}