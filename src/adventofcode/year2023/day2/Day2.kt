package src.adventofcode.year2023.day2

import src.adventofcode.utils.readAoC2023File
import java.io.File
import kotlin.math.max

fun main() {
    part1()
    part2()
}

private fun part1() {
    val exampleAnswer = readFile("example1.txt").part1Answer()
    println("Example #1 answer (8): $exampleAnswer")

    val inputAnswer = readFile("input1.txt").part1Answer()
    println("Input #1 answer: $inputAnswer")
}

private fun part2() {
    val exampleAnswer = readFile("example1.txt").part2Answer()
    println("Example #2 answer (2286): $exampleAnswer")

    val inputAnswer = readFile("input1.txt").part2Answer()
    println("Input #2 answer: $inputAnswer")
}

private fun File.part1Answer(
    expectedRed: Int = 12,
    expectedGreen: Int = 13,
    expectedBlue: Int = 14
): Int = readLines()
    .asSequence()
    .map { it.toGame() }
    .filter { game ->
        game.sets.all { it.red <= expectedRed && it.green <= expectedGreen && it.blue <= expectedBlue }
    }
    .sumOf { it.id }

private fun File.part2Answer(): Int = readLines()
    .asSequence()
    .map { it.toGame() }
    .sumOf { it.power() }

private fun readFile(name: String): File = readAoC2023File("day2/$name")

private fun String.toGame(): Game {
    val (idString, remaining) = this
        .drop(n = 5) // "Game "
        .split(": ")

    val sets = remaining
        .split("; ")
        .map { it.toSet() }

    return Game(
        id = idString.toInt(),
        sets = sets
    )
}

private fun String.toSet(): Set {
    var red = 0
    var green = 0
    var blue = 0

    split(", ").map { cubeString ->
        val (count, color) = cubeString.split(" ")
        when (color.toCube()) {
            Cube.Red -> red += count.toInt()
            Cube.Green -> green += count.toInt()
            Cube.Blue -> blue += count.toInt()
        }
    }

    return Set(red, green, blue)
}

private fun String.toCube(): Cube = Cube.values().first { it.color == this }

private fun Game.power(): Int {
    var maxRed = 0
    var maxGreen = 0
    var maxBlue = 0
    sets.forEach {
        maxRed = max(it.red, maxRed)
        maxGreen = max(it.green, maxGreen)
        maxBlue = max(it.blue, maxBlue)
    }

    if (maxRed == 0) maxRed++
    if (maxGreen == 0) maxGreen++
    if (maxBlue == 0) maxBlue++

    return maxRed * maxGreen * maxBlue
}

private data class Game(
    val id: Int,
    val sets: List<Set>
)

private data class Set(
    val red: Int,
    val green: Int,
    val blue: Int
)

private enum class Cube(val color: String) {
    Red(color = "red"),
    Green(color = "green"),
    Blue(color = "blue")
}
