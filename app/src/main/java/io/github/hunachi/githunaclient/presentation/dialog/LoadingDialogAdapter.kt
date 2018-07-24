package io.github.hunachi.githunaclient.presentation.dialog

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import io.github.hunachi.githunaclient.R
import io.github.hunachi.githunaclient.databinding.DialogLoadingBinding
import io.github.hunachi.githunaclient.presentation.dialog.base.BaseAlertDialogAdapter

/**
 * Created by hunachi on 2018/01/31.
 */
class LoadingDialogAdapter(override val context: Context) : BaseAlertDialogAdapter {
    
    private val binding: DialogLoadingBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.dialog_loading,
        null,
        false
    )
    
    override fun onCreateDialog(): AlertDialog =
            AlertDialog.Builder(context)
                    .setView(binding.root)
                    .create().apply {
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
}
