package dev.mslalith.core

import java.io.File

object RunAllProblems {

    @JvmStatic
    fun main(args: Array<String>) {
        println()
        getProblemFileTypes().forEach { problemType ->
            println("==========================================")
            println("======= Running ${getProblemTypeName(problemType)} problems =======")
            println("==========================================")
            println()
            getProblemFilesForType(problemType).forEach { problemFile ->
                runProblemFile(problemFile)
            }
        }
        println()
    }

    private fun getProblemFileTypes(): List<File> {
        val srcDirectory = File("./src").canonicalFile
        return srcDirectory.listFiles()?.mapNotNull { file ->
            file.listFiles()?.firstOrNull { it.path.endsWith("problems") }
        } ?: emptyList()
    }

    private fun getProblemFilesForType(file: File): List<File> {
        return file.listFiles()?.filterNotNull() ?: emptyList()
    }

    private fun getProblemTypeName(file: File): String {
        val typeName = file.path.split("src/").last().split("/").first()
        return typeName.split("_").joinToString(separator = " ") {
            it.first().uppercaseChar() + it.substring(1)
        }
    }

    private fun runProblemFile(file: File) {
        val problemName = file.path.split("/").last()
        val clazz = classFromFile(file)
        val instance = clazz?.constructors?.firstOrNull()?.newInstance()
        if (instance is Problem<*, *>) run(instance)
        else println("⚠️ Skipping $problemName (not a Problem)")
    }

    private fun classFromFile(file: File): Class<*>? = try {
        val packageName = "src" + file.parentFile.path.split("src")[1].replace("/", ".")
        Class.forName("$packageName.${file.nameWithoutExtension}")
    } catch (e: ClassNotFoundException) {
        println("⚠️ Skipping file: $file (not found)")
        null
    }

    private fun run(problem: Problem<*, *>) {
        buildString {
            val testResult = problem.runSilent()
            append("Running ${problem.javaClass.simpleName}")

            val testSuffix = if (testResult.allTests > 1) "s" else ""
            append(": (${testResult.allTests}) test$testSuffix")
            appendLine()

            val passedText = if (testResult.hasAllTestsPassed()) "All" else testResult.passed.toString()
            append("✅ $passedText Passed")
            appendLine()

            if (testResult.failed > 0) {
                append("❌ ${testResult.failed} Failed")
                appendLine()
            }
        }.also(::println)
    }
}
