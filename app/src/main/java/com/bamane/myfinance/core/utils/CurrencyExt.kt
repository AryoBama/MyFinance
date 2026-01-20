package com.bamane.myfinance.core.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow

fun Int.toRupiah(): String {
    val localeID = Locale("id", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    numberFormat.maximumFractionDigits = 0

    return numberFormat.format(this)
}

fun Double.toRupiah(): String {
    val localeID = Locale("id", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(this)
}

fun Double.toCompactRupiah(): String {
    if (this < 1000) return "Rp${this.toInt()}"

    val suffixes = charArrayOf(' ', 'k', 'M', 'B', 'T')
    val exp = (log10(this)).toInt()
    val index = exp / 3

    if (index >= suffixes.size) return this.toRupiah()

    val divisor = 10.0.pow(index * 3)
    val value = this / divisor

    return "Rp" + DecimalFormat("#.#").format(value) + suffixes[index]
}

fun Int.toCompactRupiah(): String {
    return this.toDouble().toCompactRupiah()
}