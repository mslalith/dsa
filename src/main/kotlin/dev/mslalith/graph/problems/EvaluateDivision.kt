package dev.mslalith.graph.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray

class EvaluateDivision : TestCaseProblem<EvaluateDivisionParams, DoubleArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EvaluateDivision().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<EvaluateDivisionParams, DoubleArray>> = arrayOf(
        TestCase(
            input = EvaluateDivisionParams(
                equations = listOf(
                    listOf("a", "b"),
                    listOf("b", "c")
                ),
                values = doubleArrayOf(2.0, 3.0),
                queries = listOf(
                    listOf("a", "c"),
                    listOf("b", "a"),
                    listOf("a", "e"),
                    listOf("a", "a"),
                    listOf("x", "x")
                )
            ),
            output = doubleArrayOf(6.0, 0.5, -1.0, 1.0, -1.0)
        ),
        TestCase(
            input = EvaluateDivisionParams(
                equations = listOf(
                    listOf("a", "b"),
                    listOf("b", "c"),
                    listOf("bc", "cd")
                ),
                values = doubleArrayOf(1.5, 2.5, 5.0),
                queries = listOf(
                    listOf("a", "c"),
                    listOf("c", "b"),
                    listOf("bc", "cd"),
                    listOf("cd", "bc")
                )
            ),
            output = doubleArrayOf(3.75, 0.4, 5.0, 0.2)
        )
    )

    override fun solve(testCaseInput: EvaluateDivisionParams): DoubleArray {
        return calcEquation(testCaseInput.equations, testCaseInput.values, testCaseInput.queries)
    }

    private fun calcEquation(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>
    ): DoubleArray {
        val hashMap = hashMapOf<String, ArrayList<Pair<String, Double>>>()
        val result = DoubleArray(queries.size) { -1.0 }

        for (i in equations.indices) {
            val (a, b) = equations[i]
            hashMap[a] = hashMap.getOrDefault(a, arrayListOf()).apply { add(b to values[i]) }
            hashMap[b] = hashMap.getOrDefault(b, arrayListOf()).apply { add(a to 1 / values[i]) }
        }

        val visited = linkedSetOf<String>()

        fun dfs(node: String, dst: String, pathProduct: Double): Double {
            if (node in visited) return -1.0
            if (node == dst) return pathProduct

            visited += node

            var pathProd = -1.0
            hashMap[node]?.forEach {
                val res = dfs(it.first, dst, pathProduct * it.second)
                if (res != -1.0) pathProd = res
            }

            return pathProd
        }

        for (i in queries.indices) {
            val (a, b) = queries[i]
            if (!hashMap.contains(a) || !hashMap.contains(b)) continue

            visited.clear()
            result[i] = dfs(a, b, 1.0)
        }

        return result
    }
}

data class EvaluateDivisionParams(
    val equations: List<List<String>>,
    val values: DoubleArray,
    val queries: List<List<String>>
) {
    override fun toString(): String {
        return """
            
            equations: $equations
            values: ${stringFromArray(values)}
            queries: $queries
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EvaluateDivisionParams

        if (equations != other.equations) return false
        if (!values.contentEquals(other.values)) return false
        if (queries != other.queries) return false

        return true
    }

    override fun hashCode(): Int {
        var result = equations.hashCode()
        result = 31 * result + values.contentHashCode()
        result = 31 * result + queries.hashCode()
        return result
    }
}
