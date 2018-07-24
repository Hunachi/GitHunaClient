package io.github.hunachi.githunaclient.util.extension

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by hunachi on 2018/02/08.
 */
class StringKtTest{
    
    @Test
    fun convertor(){
        assertEquals("HogeHogeHogeHogeHogeEvent".convertToLowerText(),"hoge hoge hoge hoge hoge")
    }
    
}
