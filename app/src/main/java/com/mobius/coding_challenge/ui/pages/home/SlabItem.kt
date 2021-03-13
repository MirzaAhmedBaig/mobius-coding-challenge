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