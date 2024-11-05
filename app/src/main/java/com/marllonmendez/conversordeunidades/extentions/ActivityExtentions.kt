package com.marllonmendez.conversordeunidades.extentions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var currentFocus = this. currentFocus
    if (currentFocus == null) {
        currentFocus = View(this)

        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}

