package com.mobius.coding_challenge.ui.pages.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobius.coding_challenge.databinding.ActivityCouponsBinding
import com.mobius.coding_challenge.ui.base.BaseVMActivity
import com.mobius.coding_challenge.ui.models.Coupon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CouponsActivity : BaseVMActivity<CouponsViewModel, ActivityCouponsBinding>() {

    override fun getViewBinding(): ActivityCouponsBinding =
        ActivityCouponsBinding.inflate(layoutInflater)

    override fun getClassName(): String = CouponsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        //show loading
        observeCoupons()
        viewModel.getCoupons()
    }

    private fun observeCoupons() {
        viewModel.couponResponse.observe(this, Observer {
            it.checkResponse(onSuccess = {
                //hide loader
                it.value.takeIf { it.isNotEmpty() }?.let { loadCouponList(it) } ?: Toast.makeText(
                    this@CouponsActivity,
                    "No data found",
                    Toast.LENGTH_SHORT
                ).show()
            }, onError = {
                //hide loader
                handleError(it)
            })
        })
    }

    private fun loadCouponList(data: List<Coupon>) {
        Log.d(TAG, "loadCouponList : Size : ${data.size}\nData : $data")
    }
}