package io.github.hunachi.githunaclient.presentation.dialog

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
/**
 * Created by hunachi on 2018/02/19.
 */
class WarningDialogAdapter(private val context: Context) {
    
    fun onCreateDialog(callback: (DialogStatus) -> Unit): AlertDialog =
            AlertDialog.Builder(context)
                    .setTitle("attention")
                    .setMessage("you'll redirect website.\n Is it ok??")
                    .setNegativeButton("NG", { _: DialogInterface, _: Int -> callback(DialogStatus.NG) })
                    .setPositiveButton("OK", { _: DialogInterface, _: Int -> callback(DialogStatus.OK) })
                    .create()
    
}
