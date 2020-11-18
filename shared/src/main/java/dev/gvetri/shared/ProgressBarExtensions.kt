package dev.gvetri.shared

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.toggle(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}