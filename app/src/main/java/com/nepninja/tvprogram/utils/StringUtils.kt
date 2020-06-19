package com.nepninja.tvprogram.utils

object StringUtils {
    @JvmStatic
    fun capitalizeCategory(category: String): String {
        if (category.contains(",")) {
            return category.split(",").joinToString(",") { it.capitalize() }
        }
        return category.capitalize()
    }

}