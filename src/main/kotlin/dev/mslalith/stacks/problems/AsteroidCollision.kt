package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*
import kotlin.math.absoluteValue

class AsteroidCollision : TestCaseProblem<IntArray, IntArray>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AsteroidCollision().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(5,10,-5),
            output = intArrayOf(5,10)
        ),
        TestCase(
            input = intArrayOf(8,-8),
            output = intArrayOf()
        ),
        TestCase(
            input = intArrayOf(10,2,-5),
            output = intArrayOf(10)
        ),
        TestCase(
            input = intArrayOf(-2,-1,1,2),
            output = intArrayOf(-2,-1,1,2)
        ),
        TestCase(
            input = intArrayOf(-2,-2,1,-2),
            output = intArrayOf(-2,-2,-2)
        ),
        TestCase(
            input = intArrayOf(-2,-1,1,-2),
            output = intArrayOf(-2,-1,-2)
        ),
        TestCase(
            input = intArrayOf(1,-2,-2,-2),
            output = intArrayOf(-2,-2,-2)
        )
    )
    
    override fun solve(testCaseInput: IntArray): IntArray {
        return asteroidCollision(testCaseInput)
    }

    private fun asteroidCollision(asteroids: IntArray): IntArray {
        val n = asteroids.size
        if (n == 0 || n == 1) return asteroids

        val stack = Stack<Int>()

        for (ast in asteroids) {
            if (stack.isEmpty() || ast > 0) {
                stack.push(ast)
            } else {
                while (stack.isNotEmpty()) {
                    val peek = stack.peek()
                    when {
                        peek > 0 && peek.absoluteValue > ast.absoluteValue -> break
                        peek > 0 && peek.absoluteValue < ast.absoluteValue -> {
                            stack.pop()
                            if (stack.isEmpty()) {
                                stack.push(ast)
                                break
                            }
                        }
                        peek > 0 && peek.absoluteValue == ast.absoluteValue -> {
                            stack.pop()
                            break
                        }
                        else -> {
                            stack.push(ast)
                            break
                        }
                    }
                }
            }
        }

        return stack.toIntArray()
    }
}
