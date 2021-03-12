package com.mobius.coding_challenge.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceManager @Inject constructor(@ApplicationContext val applicationContext: Context) {

    fun getString(resId: Int): String {
        return applicationContext.getString(resId)
    }
}