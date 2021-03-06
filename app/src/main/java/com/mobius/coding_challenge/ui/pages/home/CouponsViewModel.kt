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
package com.mobius.coding_challenge.ui.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobius.coding_challenge.data.network.Resource
import com.mobius.coding_challenge.data.repositories.DataRepository
import com.mobius.coding_challenge.ui.base.BaseViewModel
import com.mobius.coding_challenge.ui.models.Coupon
import kotlinx.coroutines.launch

class CouponsViewModel(dataRepository: DataRepository) : BaseViewModel(dataRepository) {

    private var _couponResponse: MutableLiveData<Resource<List<Coupon>>> =
        MutableLiveData()
    val couponResponse: LiveData<Resource<List<Coupon>>>
        get() = _couponResponse

    fun getCoupons() = viewModelScope.launch {
        _couponResponse.value = dataRepository.getCoupons()
    }
}
