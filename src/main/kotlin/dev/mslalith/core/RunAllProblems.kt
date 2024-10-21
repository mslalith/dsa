package dev.mslalith.core

import dev.mslalith.core.problem.Problem
import dev.mslalith.core.problem.SimpleProblem
import dev.mslalith.core.problem.TestCaseProblem
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
            files.forEach { runProblemFile(it) }
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
        when (val instance = clazz?.constructors?.firstOrNull()?.newInstance() as? Problem) {
            is SimpleProblem -> runSimpleProblem(instance)
            is TestCaseProblem<*, *> -> runTestCaseProblem(instance)
            null -> println("⚠️ Skipping $problemName (not a Problem)")
        }
    }

    private fun classFromFile(file: File): Class<*>? = try {
        val packageName = file.parentFile.path.split("src")[1].replace("/", ".").replace(".main.kotlin.", "")
        Class.forName("$packageName.${file.nameWithoutExtension}")
    } catch (e: ClassNotFoundException) {
        println("⚠️ Skipping file: $file (not found)")
        null
    }

    private fun runSimpleProblem(simpleProblem: SimpleProblem) = buildString {
        val isSuccess = simpleProblem.run()

        append("Running ${simpleProblem.javaClass.simpleName}")
        appendLine()

        if (isSuccess) append("✅ Passed") else append("❌ Failed")
        appendLine()
    }.let(::println)

    private fun runTestCaseProblem(testCaseProblem: TestCaseProblem<*, *>) = buildString {
        val testResult = testCaseProblem.runSilent()
        append("Running ${testCaseProblem.javaClass.simpleName}")

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
    }.let(::println)
}
