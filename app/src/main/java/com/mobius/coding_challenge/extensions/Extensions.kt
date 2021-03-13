/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobius.coding_challenge.extensions

import android.content.res.Resources
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import java.text.SimpleDateFormat
import java.util.*

fun String.getDateWithServerTimeStamp(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    dateFormat.timeZone = TimeZone.getDefault() // IMP !!!
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
    dateFormat.timeZone = TimeZone.getDefault() // IMP !!!
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
