package com.ibrah.emarket.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketItem (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : String,
    @ColumnInfo(name = "product")
    var product: Product,
    @ColumnInfo(name = "count")
    var count : Int
)