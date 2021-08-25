package com.eddy.healiostest.ui.extension

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(text: String) {
    Snackbar
        .make( this.requireActivity().findViewById(android.R.id.content),
            text, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showSnackbar(text: String, actionTitle: String, actionListener: View.OnClickListener) {
    Snackbar
        .make( this.requireActivity().findViewById(android.R.id.content),
            text, Snackbar.LENGTH_SHORT)
        .setAction(actionTitle, actionListener)
        .show()
}