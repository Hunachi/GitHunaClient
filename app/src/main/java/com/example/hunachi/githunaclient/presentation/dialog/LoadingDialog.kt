package com.example.hunachi.githunaclient.presentation.dialog

import android.app.Application
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.DialogLoadingBinding
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/01/31.
 */
class LoadingDialog(private val context: Context) {
    
    private val dialog: AlertDialog
    private val binding: DialogLoadingBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_loading,
            null,
            false
    )
    
    init {
        dialog = AlertDialog.Builder(context)
                .setView(binding.root)
                .create().apply {
                    window.setBackgroundDrawable(ColorDrawable(Color.GREEN))
                }
    }
    
    fun show(){
//        if(!dialog.isShowing) dialog.show()
    }
    
    fun destroy(){
        if(dialog.isShowing) dialog.dismiss()
    }
    
}
