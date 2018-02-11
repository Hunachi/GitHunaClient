package com.example.hunachi.githunaclient.data.repository

import androidx.net.toUri
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.model.Key_
import com.example.hunachi.githunaclient.util.TestSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.http.Url
import java.net.URL

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
            githubApiRepository.followerEvent(user?.userName!!, 1)
                    .subscribe({
                        assert(it.size > 10)
                    }, {
                        it.printStackTrace()
                        assert(false)
                    })
    }
    
    private fun contribution(){
        val url = "https://github.com/users/hunachi/contributions"
        githubApiRepository.contribution(url)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    assertEquals(it.toString(), "hoge!") //ha???? nannde toorunnen.
                },{
                    it.printStackTrace()
                })
    }
    
    @Test
    fun testAll(){
        user()
        contribution()
    }
}