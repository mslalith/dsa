package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class LRUCache : TestCaseProblem<Pair<Int, List<List<Int>>>, List<Int?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LRUCache().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, List<List<Int>>>, List<Int?>>> = arrayOf(
        TestCase(
            input = 2 to listOf(
                listOf(1, 1),
                listOf(2, 2),
                listOf(1),
                listOf(3, 3),
                listOf(2),
                listOf(4, 4),
                listOf(1),
                listOf(3),
                listOf(4)
            ),
            output = listOf(null, null, null, 1, null, -1, null, -1, 3, 4)
        )
    )

    override fun solve(testCaseInput: Pair<Int, List<List<Int>>>): List<Int?> = buildList {
        val (size, entries) = testCaseInput
        val cache = LRUCacheImpl(size)
        add(null)

        entries.forEach {
            val a = it[0]
            val b = it.getOrNull(1)
            if (b != null) {
                cache.put(a, b)
                add(null)
            } else {
                add(cache.get(a))
            }
        }
    }
}

private class LRUCacheImpl(
    private val capacity: Int
) {

    private val head = DoublyListNode(-1, -1)
    private val tail = DoublyListNode(-1, -1)

    private val seen = hashMapOf<Int, DoublyListNode>()

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val node = seen[key] ?: return -1

        deleteNode(node)
        addNode(node)

        return node.value
    }

    fun put(key: Int, value: Int) {
        if (seen.contains(key)) {
            val node = seen.getValue(key)
            node.value = value
            deleteNode(node)
            addNode(node)
        } else {
            val node = DoublyListNode(key, value)
            if (seen.size == capacity) {
                val prev = tail.prev
                if (prev != null) {
                    deleteNode(prev)
                    seen.remove(prev.key)
                }
            }
            seen[key] = node
            addNode(node)
        }
    }

    private fun addNode(node: DoublyListNode) {
        val next = head.next
        node.next = next
        head.next = node

        next?.prev = node
        node.prev = head
    }

    private fun deleteNode(node: DoublyListNode) {
        val prev = node.prev
        val next = node.next
        prev?.next = next
        next?.prev = prev
    }
}

private data class DoublyListNode(
    val key: Int,
    var value: Int,
    var prev: DoublyListNode? = null,
    var next: DoublyListNode? = null
)
