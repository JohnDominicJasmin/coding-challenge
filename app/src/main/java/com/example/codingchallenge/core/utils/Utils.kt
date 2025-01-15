package com.example.codingchallenge.core.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Utils {
    fun formatDate(dateString: String): String {
        val formatter = DateTimeFormatter
            .ofPattern("MMMM dd, yyyy hh:mm:ss a")
            .withZone(ZoneId.systemDefault())

        val instant = Instant.parse(dateString)
        return formatter.format(instant)
    }
}