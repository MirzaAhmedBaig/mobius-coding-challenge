package com.mobius.coding_challenge.data.network

import com.mobius.coding_challenge.ui.models.Coupon
import retrofit2.http.GET

interface ApiManager {
    @GET(EndPoints.GET_COUPONS)
    suspend fun getCoupons(): List<Coupon>
}
