package com.schaefer.home.presentation.logout

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.schaefer.home.R

internal fun Context.logoutAlert(navigateLogout: () -> Unit) {
    val builder = AlertDialog.Builder(this)

    with(builder)
    {
        setTitle(R.string.logout_dialog_title)
        setPositiveButton(
            R.string.common_yes
        ) { _: DialogInterface, _: Int ->
            navigateLogout()
        }
        setNegativeButton(
            R.string.common_cancel
        ) { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        show().also {
            it.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(this@logoutAlert, R.color.red))
            it.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(this@logoutAlert, R.color.gray))
        }
    }
}