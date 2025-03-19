package com.example.guardia.domain.utils

import com.example.guardia.domain.utils.StringUtils.ENCODER
import java.net.URLEncoder

fun String.encodeUtf8(): String? {
    return URLEncoder.encode(this, ENCODER)
}

object StringUtils {
    const val ENCODER = "UTF-8"
}