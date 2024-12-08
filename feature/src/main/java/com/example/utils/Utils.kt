package com.example.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.getMonthFromDate(): String {
    // Create a SimpleDateFormat to parse the date string
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parsedDate: Date = format.parse(this) ?: Date()  // Fallback to current date if parsing fails
    // Get the month name or number from the Date object
    val calendar = Calendar.getInstance()
    calendar.time = parsedDate
    return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) ?: "2024-12-31"
}