package dev.mslalith.core

import java.io.File

object RunAllProblems {

    @JvmStatic
    fun main(args: Array<String>) {
        println()
        getProblemBasedOnTopics().forEach { (topic, files) ->
            println("==========================================")
            println("======= Running ${topic.displayName} problems =======")
            println("==========================================")
            println()
            files.forEach { problemFile ->
                runProblemFile(problemFile)
            }
        }
        println()
    }

    private fun getProblemBasedOnTopics(): List<Pair<Topic, List<File>>> {
        val basePath = "./src/main/kotlin/dev/mslalith/"
        return Topic.entries.map { topic ->
            val files = File(basePath + topic.dirName + "/problems")
                .listFiles()
                ?.filterNotNull()
                ?: emptyList()
            topic to files
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
        val packageName = file.parentFile.path.split("src")[1].replace("/", ".").replace(".main.kotlin.", "")
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
