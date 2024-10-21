package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class ValidateIPAddress : Problem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidateIPAddress().run()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(input = "172.16.254.1", output = "IPv4"),
        TestCase(input = "2001:0db8:85a3:0:0:8A2E:0370:7334", output = "IPv6"),
        TestCase(input = "256.256.256.256", output = "Neither"),
        TestCase(input = "01.01.01.01", output = "Neither"),
        TestCase(input = "2001:0db8:85a3:00000:0:8A2E:0370:7334", output = "Neither"),
        TestCase(input = "2001:db8:85a3:0::8a2E:0370:7334", output = "Neither"),
        TestCase(input = "f:f:f:f:f:f:f:f", output = "IPv6"),
        TestCase(input = "192.0.0.1", output = "IPv4"),
        TestCase(input = "219.219.219.219", output = "IPv4")
    )

    override fun solve(testCaseInput: String): String {
        return validIPAddress(testCaseInput)
    }

    private fun validIPAddress(queryIP: String): String {
        val ipv4Pattern = """(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])""".toRegex()
        val ipv6Pattern = """^(([0-9a-fA-F]{1,4}):){7}([0-9a-fA-F]{1,4})""".toRegex()

        return when {
            ipv4Pattern.matches(queryIP) -> "IPv4"
            ipv6Pattern.matches(queryIP) -> "IPv6"
            else -> "Neither"
        }
    }
}