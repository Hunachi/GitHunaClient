package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.model.Key
import com.example.hunachi.githunaclient.util.TestSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepositoryTest {
    
    
    private lateinit var scheduler: SchedulerProvider
    private lateinit var githubApiRepository: GithubApiRepository
    private var user: User? = null
    
    @Before
    fun init() {
        scheduler = TestSchedulerProvider()
        githubApiRepository = GithubApiRepository(Key.token, "hunachi")
    }
    
    /*生まれて初めて書いたテストが通って嬉しい(((o(*ﾟ▽ﾟ*)o)))！！*/
    @Test
    fun user() {
        githubApiRepository.user("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(it.userName == "Hunachi")
                    user = it
                }, {
                    it.printStackTrace()
                    assertTrue(false)
                })
    }
    
    @Test
    fun followerEvent() {
        githubApiRepository.followerEvent("hunachi")
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
    fun feed(){
        githubApiRepository.feed("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(it.isNotEmpty())
                },{
                    it.printStackTrace()
                    assertTrue(false)
                })
    }
    
    @Test
    fun repository() {
        githubApiRepository.repository("gedorinku", "circlearning")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertEquals(it.name, "circlearning")
                }, {
                    it.printStackTrace()
                })
    }
    
    @Test
    fun follower() {
        githubApiRepository.follower("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(it.find { it.userName == "gedorinku" } != null)
                }, {
                    it.printStackTrace()
                })
    }
    
    @Test
    fun following() {
        githubApiRepository.following("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(it.find { it.userName == "gedorinku" } != null)
                }, {
                    it.printStackTrace()
                })
    }
    
    @Test
    fun gists(){
        githubApiRepository.gists("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(it.isNotEmpty())
                },{
                    it.printStackTrace()
                })
    }
    
    @Test
    fun staredGists(){
        githubApiRepository.staredGists()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(true)
                },{
                    assertTrue(false)
                    it.printStackTrace()
                })
    }
    
    @Test
    fun watchingRepo(){
        githubApiRepository.watchingRepo("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(it.isNotEmpty())
                },{
                    it.printStackTrace()
                })
    }
    
    @Test
    fun staring(){
        githubApiRepository.staring("hunachi")
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertTrue(it.isNotEmpty())
                },{
                    it.printStackTrace()
                })
    }
    
}