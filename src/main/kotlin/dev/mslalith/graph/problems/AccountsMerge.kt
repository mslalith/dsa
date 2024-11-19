package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.impl.disjointset.DisjointSet
import dev.mslalith.utils.unOrderEquals

class AccountsMerge : TestCaseProblem<List<List<String>>, List<List<String>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AccountsMerge().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<List<List<String>>, List<List<String>>>> = arrayOf(
        TestCase(
            input = listOf(
                listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                listOf("John", "johnsmith@mail.com", "john00@mail.com"),
                listOf("Mary", "mary@mail.com"),
                listOf("John", "johnnybravo@mail.com")
            ),
            output = listOf(
                listOf("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                listOf("Mary", "mary@mail.com"),
                listOf("John", "johnnybravo@mail.com")
            )
        ),
        TestCase(
            input = listOf(
                listOf("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"),
                listOf("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"),
                listOf("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"),
                listOf("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"),
                listOf("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co")
            ),
            output = listOf(
                listOf("Ethan", "Ethan0@m.co", "Ethan4@m.co", "Ethan5@m.co"),
                listOf("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe3@m.co"),
                listOf("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co", "Hanzo3@m.co"),
                listOf("Kevin", "Kevin0@m.co", "Kevin3@m.co", "Kevin5@m.co"),
                listOf("Fern", "Fern0@m.co", "Fern1@m.co", "Fern5@m.co")
            )
        )
    )

    override fun isTestPassed(actual: List<List<String>>, expected: List<List<String>>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun solve(testCaseInput: List<List<String>>): List<List<String>> {
        return accountsMerge(testCaseInput)
    }

    private fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val n = accounts.size
        val ds = DisjointSet(n)
        val map = hashMapOf<String, Int>()

        for (i in accounts.indices) {
            val row = accounts[i]
            for (j in 1 until row.size) {
                val account = row[j]
                if (!map.containsKey(account)) {
                    map[account] = i
                } else {
                    ds.union(i, map.getValue(account))
                }
            }
        }

        val mergedAccounts = Array(n) { mutableListOf<String>() }
        for ((mail, index) in map) {
            val parent = ds.findParent(index)
            mergedAccounts[parent].add(mail)
        }

        val result = mutableListOf<List<String>>()

        for (i in 0 until n) {
            if (mergedAccounts[i].isEmpty()) continue
            mergedAccounts[i].sort()
            val name = accounts[i][0]
            val temp = mutableListOf<String>()
            temp.add(name)
            for (account in mergedAccounts[i]) temp.add(account)
            result.add(temp)
        }

        return result
    }
}
