package com.mobius.coding_challenge.ui.models

data class Coupon(
    val _cls: String,
    val bonus_booster: Any,
    val bonus_image_back: String,
    val bonus_image_front: String,
    val campaign: String,
    val code: String,
    val created_at: String,
    val days_since_last_purchase_min: Int,
    val eligibility_user_levels: List<Int>,
    val eligibility_user_segments: List<String>,
    val id: String,
    val is_active: Boolean,
    val is_bonus_booster_enabled: Boolean,
    val is_deleted: Boolean,
    val last_updated_at: String,
    val ribbon_msg: String,
    val slabs: List<Slab>,
    val tab_type: String,
    val tags: Tags,
    val user_limit: Int,
    val user_redeem_limit: Int,
    val user_segmentation_type: String,
    val valid_from: String,
    val valid_until: String,
    val visibility_user_levels: List<Int>,
    val visibility_user_segments: List<String>,
    val wager_bonus_expiry: Int,
    val wager_to_release_ratio_denominator: Int,
    val wager_to_release_ratio_numerator: Int
)