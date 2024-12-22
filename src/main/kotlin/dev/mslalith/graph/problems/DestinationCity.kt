package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class DestinationCity : TestCaseProblem<List<List<String>>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DestinationCity().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<List<List<String>>, String>> = arrayOf(
        TestCase(
            input = listOf(
                listOf("London", "New York"),
                listOf("New York", "Lima"),
                listOf("Lima", "Sao Paulo")
            ),
            output = "Sao Paulo"
        ),
        TestCase(
            input = listOf(
                listOf("B", "C"),
                listOf("D", "B"),
                listOf("C", "A")
            ),
            output = "A"
        )
    )

    override fun solve(testCaseInput: List<List<String>>): String {
        return destCity(testCaseInput)
    }

    private fun destCity(paths: List<List<String>>): String {
        val outDegreeMap = hashMapOf<String, Int>()

        for (path in paths) outDegreeMap[path[0]] = outDegreeMap.getOrDefault(path[0], 0) + 1
        for (path in paths) if (path[1] !in outDegreeMap) return path[1]

        return ""
    }
}
