package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterParams
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterResult
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterResult.AddPacketResult
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterResult.ForwardPacketResult
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterResult.GetPacketResult
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterType.AddPacket
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterType.ForwardPacket
import dev.mslalith.queues.problems.ImplementRouter.ImplementRouterType.GetPacket
import java.util.*


class ImplementRouter : TestCaseProblem<ImplementRouterParams, List<ImplementRouterResult>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ImplementRouter().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<ImplementRouterParams, List<ImplementRouterResult>>> = arrayOf(
        TestCase(
            input = ImplementRouterParams(
                memoryLimit = 3,
                queries = listOf(
                    AddPacket(source = 1, destination = 4, timestamp = 90),
                    AddPacket(source = 2, destination = 5, timestamp = 90),
                    AddPacket(source = 1, destination = 4, timestamp = 90),
                    AddPacket(source = 3, destination = 5, timestamp = 95),
                    AddPacket(source = 4, destination = 5, timestamp = 105),
                    ForwardPacket,
                    AddPacket(source = 5, destination = 2, timestamp = 110),
                    GetPacket(destination = 5, startTime = 100, endTime = 110)
                )
            ),
            output = listOf(
                AddPacketResult(result = true),
                AddPacketResult(result = true),
                AddPacketResult(result = false),
                AddPacketResult(result = true),
                AddPacketResult(result = true),
                ForwardPacketResult(result = listOf(2, 5, 90)),
                AddPacketResult(result = true),
                GetPacketResult(result = 1)
            )
        ),
        TestCase(
            input = ImplementRouterParams(
                memoryLimit = 2,
                queries = listOf(
                    AddPacket(source = 7, destination = 4, timestamp = 90),
                    ForwardPacket,
                    ForwardPacket
                )
            ),
            output = listOf(
                AddPacketResult(result = true),
                ForwardPacketResult(result = listOf(7, 4, 90)),
                ForwardPacketResult(result = listOf()),
            )
        )
    )

    override fun solve(testCaseInput: ImplementRouterParams): List<ImplementRouterResult> {
        val router = Router(memoryLimit = testCaseInput.memoryLimit)
        return testCaseInput.queries.map {
            when (it) {
                is AddPacket -> AddPacketResult(
                    result = router.addPacket(
                        source = it.source,
                        destination = it.destination,
                        timestamp = it.timestamp
                    )
                )
                ForwardPacket -> ForwardPacketResult(
                    result = router.forwardPacket().toList()
                )
                is GetPacket -> GetPacketResult(
                    result = router.getCount(
                        destination = it.destination,
                        startTime = it.startTime,
                        endTime = it.endTime
                    )
                )
            }
        }
    }

    private class Router(
        private val memoryLimit: Int
    ) {
        private val counts = hashMapOf<Int, MutableList<Int>>()
        private val destinationToPacketMap = hashMapOf<Long, Packet>()
        private val queue: Queue<Long> = LinkedList()

        fun addPacket(source: Int, destination: Int, timestamp: Int): Boolean {
            val packet = Packet(source, destination, timestamp)
            val key = packet.key()
            if (key in destinationToPacketMap) return false

            if (destinationToPacketMap.size >= memoryLimit) forwardPacket()

            destinationToPacketMap[key] = packet
            queue.offer(key)

            counts.getOrPut(destination) { mutableListOf() } += timestamp

            return true
        }

        fun forwardPacket(): IntArray {
            if (destinationToPacketMap.isEmpty()) return intArrayOf()

            val key = queue.poll()!!
            val packet = destinationToPacketMap.remove(key) ?: return intArrayOf()

            val list = counts[packet.destination]!!
            list.removeAt(0)

            return intArrayOf(packet.source, packet.destination, packet.timestamp)
        }

        fun getCount(destination: Int, startTime: Int, endTime: Int): Int {
            val list = counts[destination]
            if (list.isNullOrEmpty()) return 0

            val left = list.lowerBound(target = startTime)
            val right = list.upperBound(target = endTime)

            return right - left
        }

        private fun MutableList<Int>.lowerBound(target: Int): Int {
            var low = 0
            var high = size

            while (low < high) {
                val mid = (low + high) ushr 1

                if (get(mid) < target) low = mid + 1
                else high = mid
            }

            return low
        }

        private fun MutableList<Int>.upperBound(target: Int): Int {
            var low = 0
            var high = size

            while (low < high) {
                val mid = (low + high) ushr 1

                if (get(mid) <= target) low = mid + 1
                else high = mid
            }

            return low
        }

        private data class Packet(
            val source: Int,
            val destination: Int,
            val timestamp: Int
        ) {
            fun key(): Long = (source.toLong() shl 40) or (destination.toLong() shl 20) or timestamp.toLong()
        }
    }

    data class ImplementRouterParams(
        val memoryLimit: Int,
        val queries: List<ImplementRouterType>
    )

    sealed interface ImplementRouterType {
        data object ForwardPacket : ImplementRouterType

        data class AddPacket(
            val source: Int,
            val destination: Int,
            val timestamp: Int
        ) : ImplementRouterType

        data class GetPacket(
            val destination: Int,
            val startTime: Int,
            val endTime: Int
        ) : ImplementRouterType
    }

    sealed interface ImplementRouterResult {
        data class AddPacketResult(val result: Boolean) : ImplementRouterResult
        data class ForwardPacketResult(val result: List<Int>) : ImplementRouterResult
        data class GetPacketResult(val result: Int) : ImplementRouterResult
    }
}
