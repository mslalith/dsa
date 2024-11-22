package dev.mslalith.core

import dev.mslalith.core.problem.Problem
import dev.mslalith.core.problem.SimpleProblem
import dev.mslalith.core.problem.TestCaseProblem
import java.io.File

object RunAllProblems {

    @JvmStatic
    fun main(args: Array<String>) {
        println()

        var problemsCount = 0
        Topic.entries.forEach { topic ->
            problemsCount += topic.runAllProblems()
        }

        println()
        println("\uD83C\uDF89 Ran total of $problemsCount problems")
        println()
    }

    private fun Topic.runAllProblems(): Int {
        val allProblems = allProblems()
        val totalCount = allProblems.size

        var successCount = 0
        val failedClasses = mutableListOf<Class<*>?>()
        val skippedClasses = mutableListOf<Class<*>?>()

        allProblems.forEach { file ->
            val problemName = file.path.split("/").last()
            val clazz = classFromFile(file)
            val isSuccess = when (val problem = clazz?.constructors?.firstOrNull()?.newInstance() as? Problem) {
                is SimpleProblem<*> -> problem.run()
                is TestCaseProblem<*, *> -> problem.runSilent().hasAllTestsPassed()
                null -> {
                    println("⚠️ Skipping $problemName (not a Problem)")
                    null
                }
            }
            when (isSuccess) {
                true -> successCount++
                false -> failedClasses.add(clazz)
                null -> skippedClasses.add(clazz)
            }
        }

        buildString {
            if (successCount == totalCount) {
                append("✅ $displayName ($successCount problems)")
            } else {
                append("❌ $displayName ($totalCount problems)\n")
                if (successCount > 0) append("\t✅ $successCount passed\n")
                if (failedClasses.size > 0) {
                    append("\t❌ ${failedClasses.size} failed\n")
                    failedClasses.forEach { append("\t\t• ${it?.simpleName}\n") }
                }
                if (skippedClasses.size > 0) {
                    append("\t⚠️ ${skippedClasses.size} skipped\n")
                    skippedClasses.forEach { append("\t\t• ${it?.simpleName}\n") }
                }
                deleteAt(lastIndex) // remove last new line
            }
        }.let(::println)

        return totalCount
    }
}

private fun classFromFile(file: File): Class<*>? = try {
    val packageName = file.parentFile.path.split("src")[1].replace("/", ".").replace(".main.kotlin.", "")
    Class.forName("$packageName.${file.nameWithoutExtension}")
} catch (e: ClassNotFoundException) {
    null
}

private fun Topic.allProblems(): List<File> = File("./src/main/kotlin/dev/mslalith/$dirName/problems")
    .listFiles()
    ?.filterNotNull()
    ?: emptyList()

