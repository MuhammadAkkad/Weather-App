package com.muhammed.sword.weather.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Extensions {

    fun String.parseDate(format: String): LocalDate {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(format)
        val localDateTime = LocalDate.parse(this, dateTimeFormatter).atStartOfDay()
        return localDateTime.toLocalDate()
    }

}