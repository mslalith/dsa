package dev.mslalith.core

import dev.mslalith.core.problem.TestCaseProblem

class RunningProblem : TestCaseProblem<Int, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RunningProblem().runAll()
    }

    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf()

    override fun solve(testCaseInput: Int): Boolean {
        return false
    }
}
