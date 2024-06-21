package com.example.guardia.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(format: String = DEFAULT_DATE_FORMAT): Date {
    val sdf = SimpleDateFormat(format, Locale("pt", "BR"))
    return sdf.parse(this)
}

fun String.toServiceDate() = this.toDate(DATE_FORMAT_STRINGS[17])

fun String.toSafeDate(format: String = DEFAULT_DATE_FORMAT): Date? {
    return try {
        val sdf = SimpleDateFormat(format, Locale("pt", "BR"))
        sdf.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.toSafeServiceDate() = this.toSafeDate(DATE_FORMAT_STRINGS[17])

fun Date.format(format: String): String = SimpleDateFormat(
    format,
    Locale("pt", "BR")
).format(this)

@Throws(NumberFormatException::class)
fun Date.getDayAndMonthNameAndYear() =
    format("dd MMM yyyy").uppercase(Locale.getDefault()).replace(" ", " de ").lowercase()

val DATE_FORMAT_STRINGS = arrayOf(
    // HTTP formats required by RFC2616 but with any timezone.
    "EEE MMM d HH:mm:ss z yyyy",
    "EEE, dd MMM yyyy HH:mm:ss zzz", // RFC 822, updated by RFC 1123 with any TZ
    "EEEE, dd-MMM-yy HH:mm:ss zzz", // RFC 850, obsoleted by RFC 1036 with any TZ.
    "EEE MMM d HH:mm:ss yyyy", // ANSI C's asctime() format
    // Alternative formats.
    "EEE, d MMM yyyy HH:mm:ss Z",
    "EEE, dd-MMM-yyyy HH:mm:ss z",
    "EEE, dd-MMM-yyyy HH-mm-ss z",
    "EEE, dd MMM yy HH:mm:ss z",
    "EEE dd-MMM-yyyy HH:mm:ss z",
    "EEE dd MMM yyyy HH:mm:ss z",
    "EEE dd-MMM-yyyy HH-mm-ss z",
    "EEE dd-MMM-yy HH:mm:ss z",
    "EEE dd MMM yy HH:mm:ss z",
    "EEE,dd-MMM-yy HH:mm:ss z",
    "EEE,dd-MMM-yyyy HH:mm:ss z",
    "EEE, dd-MM-yyyy HH:mm:ss z",

    /* RI bug 6641315 claims a cookie of this format was once served by www.yahoo.com */
    "EEE MMM d yyyy HH:mm:ss z",
    "yyyy-MM-dd'T'HH:mm:ss"
)

const val DEFAULT_DATE_FORMAT = "dd/MM/yyyy"