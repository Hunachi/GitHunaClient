package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.model.Key
import com.example.hunachi.githunaclient.util.TestSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import org.junit.Assert.assertEquals
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
        githubApiRepository = GithubApiRepository(Key.token)
    }
    
    /*生まれて初めて書いたテストが通って嬉しい(((o(*ﾟ▽ﾟ*)o)))！！*/
    @Test
    fun user() {
        githubApiRepository.user("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assert(it.userName == "Hunachi")
                    user = it
                    //followerEvent()
                }, {
                    it.printStackTrace()
                    assert(false)
                })
    }
    
    @Test
    fun followerEvent() {
            githubApiRepository.followerEvent("hunachi", 1)
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.ui())
                    .subscribe({
                        assert(it.isNotEmpty())
                    }, {
                        it.printStackTrace()
                        assert(false)
                    })
    }
    
    @Test
    fun repository(){
        githubApiRepository.repository("gedorinku", "circlearning")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assert(it.name == "gitHunaClient")
                },{
                    it.printStackTrace()
                })
    }
    
    @Test
    fun contribution(){
        val url = "https://github.com/users/hunachi/contributions"
        githubApiRepository.contribution(url)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertEquals(it.toString(), "hoge!")
                },{
                    it.printStackTrace()
                })
    }
    
}