package src.adventofcode.year2023.day1

import src.adventofcode.utils.readAoC2023File
import src.adventofcode.year2023.day1.Direction.*
import java.io.File

fun main() {
    part1()
    part2()
}

private fun part1() {
    val exampleAnswer = readFile("example1.txt").part1Answer()
    println("Example #1 answer (142): $exampleAnswer")

    val inputAnswer = readFile("input1.txt").part1Answer()
    println("Input #1 answer: $inputAnswer")
}

private fun part2() {
    val exampleAnswer = readFile("example2.txt").part2Answer()
    println("Example #2 answer (281): $exampleAnswer")

    val inputAnswer = readFile("input2.txt").part2Answer()
    println("Input #2 answer: $inputAnswer")
}

private fun File.part1Answer(): Int = readLines()
    .sumOf { line ->
        val first = line.first { it.isDigit() }.digitToInt()
        val last = line.last { it.isDigit() }.digitToInt()
        (first * 10) + last
    }

private fun File.part2Answer(): Int = readLines()
    .sumOf { line ->
        val first = line.digitOrWordAsDigit(direction = Start)
        val last = line.digitOrWordAsDigit(direction = End)
        (first * 10) + last
    }

private fun readFile(name: String): File = readAoC2023File("day1/$name")

private fun String.digitOrWordAsDigit(direction: Direction): Int {
    val (wordIdx, word) = when (direction) {
        Start -> findAnyOf(digitWords) ?: (Int.MAX_VALUE to "")
        End -> findLastAnyOf(digitWords) ?: (Int.MIN_VALUE to "")
    }

    val (digitIdx, digit) = when (direction) {
        Start -> findAnyOf(digits) ?: (Int.MAX_VALUE to "")
        End -> findLastAnyOf(digits) ?: (Int.MIN_VALUE to "")
    }

    val shouldPickDigit = when (direction) {
        Start -> digitIdx < wordIdx
        End -> digitIdx > wordIdx
    }

    return if (shouldPickDigit) digit.toInt() else word.wordToDigit()
}

private fun String.wordToDigit(): Int = digitWords.indexOfFirst { it == this } + 1

private val digits = List(10) { "$it" }
private val digitWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

private enum class Direction {
    Start,
    End
}
