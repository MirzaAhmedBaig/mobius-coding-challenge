package com.mobius.coding_challenge.ui.models

data class Slab(
    val max: Double,
    val min: Double,
    val name: String,
    val num: Int,
    val otc_max: Double,
    val otc_percent: Double,
    val wagered_max: Double,
    val wagered_percent: Double
)