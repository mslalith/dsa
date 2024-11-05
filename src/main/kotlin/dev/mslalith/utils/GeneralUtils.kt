package dev.mslalith.utils

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.precision(n: Int): Double {
    val decimalPattern = "#".repeat(n)
    val df = DecimalFormat("#.$decimalPattern")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(this).toDouble()
}

infix fun <A, B, C> Pair<A, B>.toTriple(other: C) = Triple(first, second, other)
