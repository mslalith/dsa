package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.buildList
import dev.mslalith.utils.createClone
import java.util.*


class RemoveSubFoldersFromFilesystem : TestCaseProblem<Array<String>, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveSubFoldersFromFilesystem().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, List<String>>> = arrayOf(
        TestCase(
            input = arrayOf("/a", "/a/b", "/c/d", "/c/d/e", "/c/f"),
            output = listOf("/a", "/c/d", "/c/f")
        ),
        TestCase(
            input = arrayOf("/a", "/a/b/c", "/a/b/d"),
            output = listOf("/a")
        ),
        TestCase(
            input = arrayOf("/a/b/c", "/a/b/ca", "/a/b/d"),
            output = listOf("/a/b/c", "/a/b/ca", "/a/b/d")
        )
    )

    override fun solve(testCaseInput: Array<String>): List<String> {
        return removeSubfolders(testCaseInput.createClone())
    }

    private fun removeSubfolders(folder: Array<String>): List<String> {
        return buildList {
            folder.sort()
            for (subFolder in folder) {
                val prev = lastOrNull()
                if (prev != null && subFolder.startsWith(prev) && subFolder.getOrNull(prev.length) == '/') continue

                add(subFolder)
            }
        }
    }
}
