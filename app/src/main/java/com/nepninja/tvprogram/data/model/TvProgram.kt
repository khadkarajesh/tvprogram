package com.nepninja.tvprogram.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class TvProgram(val channel: Channel, val programmes: List<Programme>) : Parcelable

@Parcelize
data class Channel(val id: String?, val idNo: String?, val name: String?, val src: String?) :
    Parcelable

@Parcelize
data class Programme(
    val startDate: Date?,
    val stopDate: Date?,
    val channelId: String?,
    val idNo: String,
    val title: String?,
    val subTitle: String?,
    val description: String?,
    val category: List<String>,
    val src: String?
) : Parcelable

