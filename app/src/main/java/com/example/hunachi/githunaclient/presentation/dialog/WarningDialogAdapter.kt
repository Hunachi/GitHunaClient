package com.example.hunachi.githunaclient.presentation.dialog

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.example.hunachi.githunaclient.generated.callback.OnClickListener
import com.example.hunachi.githunaclient.presentation.dialog.base.BaseAlertDialogAdapter
import com.example.hunachi.githunaclient.util.StatusDialog

/**
 * Created by hunachi on 2018/02/19.
 */
class WarningDialogAdapter(private val context: Context) {
    
    fun onCreateDialog(callback: (StatusDialog) -> Unit): AlertDialog =
            AlertDialog.Builder(context)
                    .setTitle("attention")
                    .setMessage("you'll redirect website.\n Is it ok??")
                    .setNegativeButton("NG", { _: DialogInterface, _: Int -> callback(StatusDialog.NG) })
                    .setPositiveButton("OK", { _: DialogInterface, _: Int -> callback(StatusDialog.OK) })
                    .create()
    
}