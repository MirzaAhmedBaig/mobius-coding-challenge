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

import android.view.View
import com.mobius.coding_challenge.R
import com.mobius.coding_challenge.databinding.SlabItemBinding
import com.mobius.coding_challenge.ui.models.Slab
import com.xwray.groupie.viewbinding.BindableItem

class SlabItem(private val slab: Slab) : BindableItem<SlabItemBinding>() {

    override fun bind(viewBinding: SlabItemBinding, position: Int) {
        viewBinding.tvPurchaseAmtValue.text = ">=${slab.min} & <${slab.max}"
        viewBinding.tvBonusAmtValue.text = "${slab.wagered_percent}% (Max. ₹${slab.wagered_max})"
        viewBinding.tvInstantAmtValue.text = "${slab.otc_percent}% (Max. ₹${slab.otc_max})"
    }

    override fun getLayout() = R.layout.slab_item

    override fun initializeViewBinding(view: View): SlabItemBinding {
        return SlabItemBinding.bind(view)
    }
}
