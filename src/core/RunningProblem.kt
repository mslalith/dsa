package src.core

class RunningProblem : Problem<Int, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RunningProblem().run()
    }

    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf()

    override fun solve(testCaseInput: Int): Boolean {
        return false
    }
}
