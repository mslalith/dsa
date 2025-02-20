package dev.mslalith.core

data class TestCase<I, O>(
    val input: I,
    val output: O,
    val otherAcceptableOutputs: List<O> = emptyList()
)
