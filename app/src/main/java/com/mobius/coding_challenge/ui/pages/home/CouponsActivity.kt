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

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobius.coding_challenge.databinding.ActivityCouponsBinding
import com.mobius.coding_challenge.ui.base.BaseVMActivity
import com.mobius.coding_challenge.ui.models.Coupon
import com.mobius.coding_challenge.utils.LastItemMarginDecorator
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
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
        observeCoupons()
        viewModel.getCoupons()
    }

    private fun observeCoupons() {
        viewModel.couponResponse.observe(
            this,
            Observer {
                it.checkResponse(
                    onSuccess = {
                        binding.progressView.visibility = View.GONE
                        it.value.takeIf { it.isNotEmpty() }?.let { loadCouponList(it) }
                            ?: Toast.makeText(
                                this@CouponsActivity,
                                "No data found",
                                Toast.LENGTH_SHORT
                            ).show()
                    },
                    onError = {
                        binding.progressView.visibility = View.GONE
                        handleError(it)
                    }
                )
            }
        )
    }

    private fun loadCouponList(data: List<Coupon>) {
        Log.d(TAG, "loadCouponList : Size : ${data.size}\nData : $data")
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.map { CouponItem(it) })
        }

        binding.rvCoupons.apply {
            layoutManager = LinearLayoutManager(this@CouponsActivity)
            adapter = mAdapter
            addItemDecoration(LastItemMarginDecorator())
        }
    }
}
