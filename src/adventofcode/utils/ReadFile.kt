package src.adventofcode.utils

import java.io.File
import java.nio.file.Paths

fun readAoCFile(fileName: String): File {
    val workingDirectory = Paths.get("").toAbsolutePath()
    return File("${workingDirectory}/src/adventofcode/$fileName")
}

fun readAoC2023File(fileName: String): File = readAoCFile("year2023/$fileName")
