package com.hunachi.hunachi.githunaclient.presentation.dialog.base

import android.content.Context
import android.support.v7.app.AlertDialog


/**
 * Created by hunachi on 2018/02/04.
 */
interface BaseAlertDialogAdapter {
    
    val context: Context
    
    fun onCreateDialog(): AlertDialog

}