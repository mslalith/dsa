package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray
import java.util.*

class MinimumGeneticMutation : TestCaseProblem<MinimumGeneticMutationParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumGeneticMutation().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MinimumGeneticMutationParams, Int>> = arrayOf(
        TestCase(
            input = MinimumGeneticMutationParams(
                startGene = "AACCGGTT",
                endGene = "AACCGGTA",
                bank = arrayOf("AACCGGTA")
            ),
            output = 1
        ),
        TestCase(
            input = MinimumGeneticMutationParams(
                startGene = "AACCGGTT",
                endGene = "AAACGGTA",
                bank = arrayOf("AACCGGTA", "AACCGCTA", "AAACGGTA")
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: MinimumGeneticMutationParams): Int {
        return minMutation(testCaseInput.startGene, testCaseInput.endGene, testCaseInput.bank)
    }

    private fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        val n = startGene.length
        val bankSet = bank.toHashSet()

        val queue: Queue<String> = LinkedList()
        queue.add(startGene)

        val visited = hashSetOf<String>()
        visited += startGene

        var steps = 0

        while (queue.isNotEmpty()) {
            val size = queue.size

            for (x in 0 until size) {
                val gene = queue.poll()
                if (gene == endGene) return steps

                for (possibleGene in bank) {
                    var count = 0
                    for (i in 0 until n) if (gene[i] != possibleGene[i]) count++
                    if (count != 1) continue

                    if (possibleGene in bankSet && possibleGene !in visited) {
                        visited += possibleGene
                        queue.add(possibleGene)
                    }
                }
            }

            steps++
        }

        return -1
    }
}

data class MinimumGeneticMutationParams(
    val startGene: String,
    val endGene: String,
    val bank: Array<String>
) {
    override fun toString(): String {
        return """
            
            startGene: $startGene
            endGene: $endGene
            bank: ${stringFromArray(bank)}
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MinimumGeneticMutationParams

        if (startGene != other.startGene) return false
        if (endGene != other.endGene) return false
        if (!bank.contentEquals(other.bank)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startGene.hashCode()
        result = 31 * result + endGene.hashCode()
        result = 31 * result + bank.contentHashCode()
        return result
    }
}
