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