package com.mobius.coding_challenge.extensions

import android.content.res.Resources
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import java.text.SimpleDateFormat
import java.util.*

fun String.getDateWithServerTimeStamp(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    dateFormat.timeZone = TimeZone.getDefault()  // IMP !!!
    return try {
        dateFormat.parse(this)
    } catch (e: Exception) {
        this.getOtherDateServeTimestamp()
    }
}

fun String.isoTimeToTimestamp(): Long {
    return this.getDateWithServerTimeStamp().time
}

fun String.getOtherDateServeTimestamp(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    dateFormat.timeZone = TimeZone.getDefault()  // IMP !!!
    return dateFormat.parse(this)
}


fun Long.toTimeString(): String {
    return try {
        val sdf = SimpleDateFormat("dd MMM, yyyy hh:mm a", Locale.US)
        val netDate = Date(this)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}


fun SpannableStringBuilder.getColoredSubText(subText: String, color: Int): SpannableStringBuilder {
    val spannableStringBuilder = SpannableStringBuilder(this)
    spannableStringBuilder.setSpan(
        ForegroundColorSpan(color),
        this.indexOf(subText),
        this.indexOf(subText) + subText.length,
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
    )
    return spannableStringBuilder
}

fun Int.dpToPx(): Int {
    return ((this * Resources.getSystem().displayMetrics.density).toInt())
}