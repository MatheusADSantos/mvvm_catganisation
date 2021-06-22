package com.schaefer.core.extension

import android.view.View
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}