package com.mobius.coding_challenge.data.repositories

import com.mobius.coding_challenge.data.network.ApiManager
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiManager: ApiManager) : BaseRepository() {
    suspend fun getCoupons() = safeApiCall { apiManager.getCoupons() }

}