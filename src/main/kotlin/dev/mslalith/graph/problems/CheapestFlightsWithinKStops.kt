package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray
import java.util.*

class CheapestFlightsWithinKStops : TestCaseProblem<CheapestFlightsWithinKStopsParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CheapestFlightsWithinKStops().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CheapestFlightsWithinKStopsParams, Int>> = arrayOf(
        TestCase(
            input = CheapestFlightsWithinKStopsParams(
                n = 4,
                flights = arrayOf(
                    intArrayOf(0, 1, 100),
                    intArrayOf(1, 2, 100),
                    intArrayOf(2, 0, 100),
                    intArrayOf(1, 3, 600),
                    intArrayOf(2, 3, 200)
                ),
                src = 0,
                dst = 3,
                k = 1
            ),
            output = 700
        ),
        TestCase(
            input = CheapestFlightsWithinKStopsParams(
                n = 3,
                flights = arrayOf(
                    intArrayOf(0, 1, 100),
                    intArrayOf(1, 2, 100),
                    intArrayOf(0, 2, 500)
                ),
                src = 0,
                dst = 2,
                k = 1
            ),
            output = 200
        ),
        TestCase(
            input = CheapestFlightsWithinKStopsParams(
                n = 3,
                flights = arrayOf(
                    intArrayOf(0, 1, 100),
                    intArrayOf(1, 2, 100),
                    intArrayOf(0, 2, 500)
                ),
                src = 0,
                dst = 2,
                k = 1
            ),
            output = 200
        ),
        TestCase(
            input = CheapestFlightsWithinKStopsParams(
                n = 11,
                flights = arrayOf(
                    intArrayOf(0, 3, 3),
                    intArrayOf(3, 4, 3),
                    intArrayOf(4, 1, 3),
                    intArrayOf(0, 5, 1),
                    intArrayOf(5, 1, 100),
                    intArrayOf(0, 6, 2),
                    intArrayOf(6, 1, 100),
                    intArrayOf(0, 7, 1),
                    intArrayOf(7, 8, 1),
                    intArrayOf(8, 9, 1),
                    intArrayOf(9, 1, 1),
                    intArrayOf(1, 10, 1),
                    intArrayOf(10, 2, 1),
                    intArrayOf(1, 2, 100)
                ),
                src = 0,
                dst = 2,
                k = 4
            ),
            output = 11
        )
    )

    override fun solve(testCaseInput: CheapestFlightsWithinKStopsParams): Int {
        return findCheapestPrice(
            n = testCaseInput.n,
            flights = testCaseInput.flights,
            src = testCaseInput.src,
            dst = testCaseInput.dst,
            k = testCaseInput.k
        )
    }

    private fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val adjList = Array(n) { mutableListOf<Pair<Int, Int>>() }
        for ((u, v, wt) in flights) adjList[u].add(v to wt)

        // (stops, cost, node)
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
        queue.add(Triple(0, 0, src))

        val distance = IntArray(n) { Int.MAX_VALUE }
        distance[src] = 0

        while (queue.isNotEmpty()) {
            val (stops, cost, node) = queue.poll()
            if (stops > k) continue

            for ((adjNode, adjWt) in adjList[node]) {
                val d = cost + adjWt
                if (d < distance[adjNode]) {
                    distance[adjNode] = d
                    queue.add(Triple(stops + 1, d, adjNode))
                }
            }
        }

        return if (distance[dst] == Int.MAX_VALUE) -1 else distance[dst]
    }
}

data class CheapestFlightsWithinKStopsParams(
    val n: Int,
    val flights: Array<IntArray>,
    val src: Int,
    val dst: Int,
    val k: Int
) {
    override fun toString(): String {
        return """
            
            n: $n
            flights: ${stringFromArray(flights)}
            src: $src
            dst: $dst
            k: $k
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CheapestFlightsWithinKStopsParams

        if (n != other.n) return false
        if (!flights.contentDeepEquals(other.flights)) return false
        if (src != other.src) return false
        if (dst != other.dst) return false
        if (k != other.k) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n
        result = 31 * result + flights.contentDeepHashCode()
        result = 31 * result + src
        result = 31 * result + dst
        result = 31 * result + k
        return result
    }
}
