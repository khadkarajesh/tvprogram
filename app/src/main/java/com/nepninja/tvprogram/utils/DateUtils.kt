package com.nepninja.tvprogram.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
    private const val APP_DATE_FORMAT = "yyyyMMddHHmmss"
    private const val APP_DATE_DISPLAY_FORMAT = "EEE, d MMM hh:mm  aa"
    fun strToDate(date: String): Date? {
        return try {
            val format = SimpleDateFormat(APP_DATE_FORMAT)
            format.parse(date)
        } catch (e: ParseException) {
            null
        }
    }

    @JvmStatic
    fun dateToStr(date: Date): String {
        return try {
            val dateFormat = SimpleDateFormat(APP_DATE_DISPLAY_FORMAT)
            return dateFormat.format(date)
        } catch (e: ParseException) {
            ""
        }
    }
}