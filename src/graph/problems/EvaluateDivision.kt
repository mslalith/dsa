package src.graph.problems

import src.core.Problem
import src.core.TestCase
import src.utils.stringFromArray

class EvaluateDivision : Problem<EvaluateDivisionParams, DoubleArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EvaluateDivision().run()
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

        var path = -1.0

        fun dfs(visited: HashSet<String>, node: String, dst: String, pathProduct: Double) {
            if (visited.contains(node)) return
            if (node == dst) {
                path = pathProduct
                return
            }

            visited.add(node)
            hashMap[node]?.forEach {
                dfs(visited, it.first, dst, pathProduct * it.second)
            }
        }

        for (i in queries.indices) {
            val (a, b) = queries[i]
            if (!hashMap.contains(a) || !hashMap.contains(b)) continue

            dfs(hashSetOf(), a, b, 1.0)
            result[i] = path
            path = -1.0
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
