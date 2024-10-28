package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*

class SimplifyPath : TestCaseProblem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SimplifyPath().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "/home/",
            output = "/home"
        ),
        TestCase(
            input = "/home//foo/",
            output = "/home/foo"
        ),
        TestCase(
            input = "/home/user/Documents/../Pictures",
            output = "/home/user/Pictures"
        ),
        TestCase(
            input = "/../",
            output = "/"
        ),
        TestCase(
            input = "/.../a/../b/c/../d/./",
            output = "/.../b/d"
        )
    )

    override fun solve(testCaseInput: String): String {
        return simplifyPath(testCaseInput)
    }

    private fun simplifyPath(path: String): String {
        if (path == "/") return path

        val stack = Stack<String>()
        val splits = path.split("/")

        for (entry in splits) {
            when (entry) {
                "", "." -> continue
                ".." -> if (stack.isNotEmpty()) stack.pop()
                else -> stack.push(entry)
            }
        }

        if (stack.isEmpty()) return "/"

        return buildString {
            while (stack.isNotEmpty()) {
                insert(0, "/" + stack.pop())
            }
        }
    }
}
