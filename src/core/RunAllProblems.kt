package src.core

import src.array.problems.FindFirstAndLastPositionOfElementInSortedArray
import src.array.problems.PlusOne
import src.array.problems.SpiralMatrix
import src.linkedlist.problems.RemoveDuplicatesFromSortedList
import src.linkedlist.problems.RemoveNthNodeFromListEnd
import src.math.problems.IsPrime
import src.math.problems.ReverseDigits
import src.math.problems.SumOfDigits
import src.stacks.problems.EvaluateExpression
import src.stacks.problems.GenerateAllParentheses
import src.stacks.problems.NearestSmallerElement
import src.stacks.problems.RedundantBraces
import src.string.problems.LongestCommonPrefix
import src.trees.problems.InvertBinaryTree
import src.trees.problems.IsValidBST
import src.trees.problems.PathSum

object RunAllProblems {

    @JvmStatic
    fun main(args: Array<String>) {
        println("======= Running Math problems =======")
        run(IsPrime())
        run(ReverseDigits())
        run(SumOfDigits())
        println()

        println("======= Running Array problems =======")
        run(PlusOne())
        run(FindFirstAndLastPositionOfElementInSortedArray())
        run(SpiralMatrix())
        println()

        println("======= Running String problems =======")
        run(LongestCommonPrefix())
        println()

        println("======= Running Stack problems =======")
        run(EvaluateExpression())
        run(GenerateAllParentheses())
        run(NearestSmallerElement())
        run(RedundantBraces())
        println()

        println("======= Running Linked List problems =======")
        run(RemoveDuplicatesFromSortedList())
        run(RemoveNthNodeFromListEnd())

        println("======= Running Tree problems =======")
        run(InvertBinaryTree())
        run(PathSum())
        run(IsValidBST())
        println()
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
