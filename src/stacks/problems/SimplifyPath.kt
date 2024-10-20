package src.stacks.problems

import src.core.Problem
import src.core.TestCase
import java.util.*

class SimplifyPath : Problem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SimplifyPath().run()
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
