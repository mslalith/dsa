package src.graph.problems

import src.core.Problem
import src.core.TestCase
import kotlin.math.absoluteValue

class ReorderRoutesToMakeAllPathsLeadToCityZero : Problem<Pair<Int, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReorderRoutesToMakeAllPathsLeadToCityZero().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 3),
                intArrayOf(2, 3),
                intArrayOf(4, 0),
                intArrayOf(4, 5)
            ),
            output = 3
        ),
        TestCase(
            input = 5 to arrayOf(
                intArrayOf(1, 0),
                intArrayOf(1, 2),
                intArrayOf(3, 2),
                intArrayOf(3, 4)
            ),
            output = 2
        ),
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(0, 2),
                intArrayOf(0, 3),
                intArrayOf(4, 1),
                intArrayOf(4, 5),
                intArrayOf(5, 0)
            ),
            output = 3
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return minReorder(testCaseInput.first, testCaseInput.second)
    }

    private fun minReorder(n: Int, connections: Array<IntArray>): Int {
        val adjList = List(n) { HashSet<Int>() }
        connections.forEach { (from, to) ->
            adjList[from].add(to)
            adjList[to].add(-from)
        }

        println(adjList)
        val visit = BooleanArray(n) { false }

        fun visitCities(from: Int): Int {
            var change = 0
            visit[from] = true

            for (city in adjList[from]) {
                if (!visit[city.absoluteValue]) {
                    if (city > 0) change++
                    change += visitCities(city.absoluteValue)
                }
            }

            return change
        }

        return visitCities(0)
    }
}
