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

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobius.coding_challenge.R
import com.mobius.coding_challenge.databinding.CouponItemBinding
import com.mobius.coding_challenge.extensions.getColoredSubText
import com.mobius.coding_challenge.extensions.isoTimeToTimestamp
import com.mobius.coding_challenge.extensions.toTimeString
import com.mobius.coding_challenge.ui.models.Coupon
import com.mobius.coding_challenge.ui.models.Slab
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import java.util.*

class CouponItem(private val coupon: Coupon) :
    BindableItem<CouponItemBinding>() {

    override fun bind(viewBinding: CouponItemBinding, position: Int) {

        viewBinding.tvCode.text = coupon.code.toUpperCase(Locale.ROOT)
        viewBinding.tvRibbonMsg.text = coupon.ribbon_msg

        val maxWageredPercent = coupon.slabs.map { it.wagered_percent }.maxOrNull() ?: 0.0
        val maOctPercent = coupon.slabs.map { it.otc_percent }.maxOrNull() ?: 0.0
        val maxWageredMax = coupon.slabs.map { it.wagered_max }.maxOrNull() ?: 0.0
        val maOctMax = coupon.slabs.map { it.otc_max }.maxOrNull() ?: 0.0

        viewBinding.tvOfferText.text =
            "Get ${maxWageredPercent + maOctPercent}% upto ₹${maOctMax + maxWageredMax}"

        viewBinding.tvValidUtil.text =
            "Valid till ${coupon.valid_until.isoTimeToTimestamp().toTimeString()}"

        viewBinding.tvMinSlab.text = "Min. Deposit\n${coupon.slabs.map { it.min }.minOrNull()}"

        setSlabs(viewBinding, coupon.slabs)

        val color = Color.parseColor("#FF9800")
        val footerOne =
            SpannableStringBuilder("For every ₹${coupon.wager_to_release_ratio_numerator} bet ₹${coupon.wager_to_release_ratio_denominator} will be released from bonus amount")
        val subTextOne = "₹${coupon.wager_to_release_ratio_numerator}"
        val subTextTwo = "₹${coupon.wager_to_release_ratio_denominator}"
        viewBinding.footerTextOne.text =
            footerOne.getColoredSubText(subTextOne, color).getColoredSubText(subTextTwo, color)

        val footerTwo =
            SpannableStringBuilder("Bonus expires ${coupon.wager_bonus_expiry} days from the issue")
        val subTextThree = "${coupon.wager_bonus_expiry} days"
        viewBinding.footerTextTwo.text = footerTwo.getColoredSubText(subTextThree, color)

        viewBinding.tvExpand.setOnClickListener {
            if (coupon.isExpanded) {
                viewBinding.tvExpand.text = "Show details"
                viewBinding.ivExpand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                viewBinding.slabList.root.visibility = View.GONE
            } else {
                viewBinding.tvExpand.text = "Hide details"
                viewBinding.ivExpand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                viewBinding.slabList.root.visibility = View.VISIBLE
            }
            coupon.isExpanded = !coupon.isExpanded
        }
        viewBinding.tvCode.setOnClickListener {
            (it.context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager)?.setPrimaryClip(
                ClipData.newPlainText("Mobius", coupon.code)
            )
            Toast.makeText(it.context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSlabs(viewBinding: CouponItemBinding, data: List<Slab>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.map { SlabItem(it) })
        }

        viewBinding.slabList.rvSlabList.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
        }
    }

    override fun getLayout() = R.layout.coupon_item

    override fun initializeViewBinding(view: View): CouponItemBinding {
        return CouponItemBinding.bind(view)
    }
}
