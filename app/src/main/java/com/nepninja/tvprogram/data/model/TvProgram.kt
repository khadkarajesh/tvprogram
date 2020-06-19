package com.nepninja.tvprogram.data.model

import java.util.*


data class TvProgram(val channel: Channel, val programmes: List<Programme>)

data class Channel(val id: String?, val idNo: String?, val name: String?, val src: String?)

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
)

