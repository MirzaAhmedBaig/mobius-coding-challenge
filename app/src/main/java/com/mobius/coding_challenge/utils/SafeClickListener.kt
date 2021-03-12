package com.mobius.coding_challenge.utils

import android.os.SystemClock
import android.util.Log
import android.view.View

class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private val TAG = SafeClickListener::class.java.simpleName
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            Log.d(TAG, "onClick rejected")
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}