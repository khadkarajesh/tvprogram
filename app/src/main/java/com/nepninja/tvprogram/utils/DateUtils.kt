package com.nepninja.tvprogram.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
    fun strToDate(date: String): Date? {
        return try {
            val format = SimpleDateFormat("yyyyMMddHHmmss")
            format.parse(date)
        } catch (e: ParseException) {
            null
        }
    }

    fun dateToStr(date: Date): String? {
        return try {
            val dateFormat = SimpleDateFormat("yyyyMMddHHmmssSSSZ")

            dateFormat.format(date)
        } catch (e: ParseException) {
            null
        }
    }
}