package com.nepninja.tvprogram.data.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Tv(@Element val channels: List<Channel>, @Element val programmes: List<Programme>)


@Xml
data class Channel(
    @Attribute val idNo: String,
    @Attribute val id: String,
    @PropertyElement(name = "display-name") val name: String,
    @Element(name = "src") val logo: Icon
)

@Xml
data class Icon(
    @Attribute(name = "src") val logo: String
)

@Xml
data class Programme(
    @Attribute(name = "start") val startTime: String,
    @Attribute(name = "end") val endTime: String,
    @Attribute(name = "channel") val channelId: String,
    @Attribute(name = "idNo") val idNo: String,
    @PropertyElement val title: String,
    @PropertyElement(name = "sub-title") val subTitle: String,
    @Attribute val category: String,
    @PropertyElement(name = "desc") val description: String,
    @Element(name = "src") val logo: Icon
)

