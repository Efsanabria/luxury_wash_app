package com.example.luxury_wash.models

import androidx.annotation.DrawableRes

data class ItemService(
    val title: String,
    val subtitle: String,
    val desc: String,
    val priceLabel: String,
    @DrawableRes val imageRes: Int
)