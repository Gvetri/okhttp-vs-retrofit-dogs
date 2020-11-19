package dev.gvetri.player

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.dailymotion.android.player.sdk.PlayerWebView

fun PlayerWebView.setFullScreen(root: ConstraintLayout) {
    layoutParams = ConstraintLayout.LayoutParams(0, 0)
    root.removeView(this)
    root.addView(this, 0)
    val constrainSet = ConstraintSet()
    constrainSet.clone(root)
    constrainSet.connect(
        id,
        ConstraintSet.TOP,
        ConstraintSet.PARENT_ID,
        ConstraintSet.TOP,
        0
    )
    constrainSet.connect(
        id,
        ConstraintSet.LEFT,
        ConstraintSet.PARENT_ID,
        ConstraintSet.LEFT,
        0
    )
    constrainSet.connect(
        id,
        ConstraintSet.RIGHT,
        ConstraintSet.PARENT_ID,
        ConstraintSet.RIGHT,
        0
    )
    constrainSet.connect(
        id,
        ConstraintSet.BOTTOM,
        ConstraintSet.PARENT_ID,
        ConstraintSet.BOTTOM,
        0
    )
    constrainSet.applyTo(root)
}

fun PlayerWebView.setPortraitMode(root: ConstraintLayout, textView: TextView) {
    val density = resources.displayMetrics.density
    val dp = 240 * density
    layoutParams = ConstraintLayout.LayoutParams(0, dp.toInt())
    root.removeView(this)
    root.addView(this, 0)
    val constrainSet = ConstraintSet()
    constrainSet.clone(root)
    constrainSet.connect(
        id,
        ConstraintSet.START,
        ConstraintSet.PARENT_ID,
        ConstraintSet.START,
        0
    )
    constrainSet.connect(
        id,
        ConstraintSet.TOP,
        ConstraintSet.PARENT_ID,
        ConstraintSet.TOP,
        0
    )
    constrainSet.connect(
        id,
        ConstraintSet.END,
        ConstraintSet.PARENT_ID,
        ConstraintSet.END,
        0
    )
    constrainSet.applyTo(root)
    textView.let { it ->
        it.layoutParams = ConstraintLayout.LayoutParams(0, 48)
        root.removeView(this)
        root.addView(this, 1)
        val titleConstraintSet = ConstraintSet()
        titleConstraintSet.clone(root)
        titleConstraintSet.connect(
            it.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            0
        )
        titleConstraintSet.connect(
            it.id,
            ConstraintSet.TOP,
            id,
            ConstraintSet.BOTTOM,
            0
        )
        titleConstraintSet.connect(
            it.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END,
            0
        )
        constrainSet.applyTo(root)
    }
}