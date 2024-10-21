package dev.mslalith.core

data class TestResult(
    val allTests: Int,
    val passed: Int,
    val failed: Int
) {
    fun hasAllTestsPassed(): Boolean = allTests == passed
}
