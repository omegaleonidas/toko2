package com.sidiq.toko.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "toko")
data class TokoModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "acoount_id")
    val account_id: String,
    @ColumnInfo(name = "account_name")
    val account_name: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "area_id")
    val area_id: String,
    @ColumnInfo(name = "area_name")
    val area_name: String,
    @ColumnInfo(name = "channel_id")
    val channel_id: String,
    @ColumnInfo(name = "hannel_name")
    val channel_name: String,

    @ColumnInfo(name = "dc_id")
    val dc_id: String,
    @ColumnInfo(name = "dc_name")
    val dc_name: String,
    @ColumnInfo(name = "latitude")
    val latitude: String,
    @ColumnInfo(name = "longitude")
    val longitude: String,
    @ColumnInfo(name = "region_id")
    val region_id: String,
    @ColumnInfo(name = "region_name")
    val region_name: String,
    @ColumnInfo(name = "store_code")
    val store_code: String,
    @ColumnInfo(name = "store_id")
    val store_id: String,
    @ColumnInfo(name = "store_name")
    val store_name: String,
    @ColumnInfo(name = "subchannel_id")
    val subchannel_id: String,
    @ColumnInfo(name = "subchannel_name")
    val subchannel_name: String

)



