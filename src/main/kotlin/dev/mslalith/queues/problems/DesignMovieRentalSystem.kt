package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemParams
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemResult
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemResult.ReportResult
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemResult.SearchResult
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemType.Drop
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemType.Rent
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemType.Report
import dev.mslalith.queues.problems.DesignMovieRentalSystem.DesignMovieRentalSystemType.Search
import dev.mslalith.utils.stringFromArray
import java.util.TreeSet

class DesignMovieRentalSystem : TestCaseProblem<DesignMovieRentalSystemParams, List<DesignMovieRentalSystemResult?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DesignMovieRentalSystem().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<DesignMovieRentalSystemParams, List<DesignMovieRentalSystemResult?>>> = arrayOf(
        TestCase(
            input = DesignMovieRentalSystemParams(
                n = 3,
                entries = arrayOf(
                    intArrayOf(0, 1, 5),
                    intArrayOf(0, 2, 6),
                    intArrayOf(0, 3, 7),
                    intArrayOf(1, 1, 4),
                    intArrayOf(1, 2, 7),
                    intArrayOf(2, 1, 5)
                ),
                queries = listOf(
                    Search(movie = 1),
                    Rent(shop = 0, movie = 1),
                    Rent(shop = 1, movie = 2),
                    Report,
                    Drop(shop = 1, movie = 2),
                    Search(movie = 2)
                )
            ),
            output = listOf(
                SearchResult(result = listOf(1, 0, 2)),
                null,
                null,
                ReportResult(
                    result = listOf(
                        listOf(0, 1),
                        listOf(1, 2),
                    )
                ),
                null,
                SearchResult(result = listOf(0, 1))
            )
        ),
        TestCase(
            input = DesignMovieRentalSystemParams(
                n = 4,
                entries = arrayOf(
                    intArrayOf(3, 3, 3),
                    intArrayOf(0, 4, 3),
                    intArrayOf(2, 3, 2)
                ),
                queries = listOf(
                    Search(movie = 3),
                    Rent(shop = 3, movie = 3),
                    Search(movie = 4),
                    Rent(shop = 0, movie = 4),
                    Report
                )
            ),
            output = listOf(
                SearchResult(result = listOf(2, 3)),
                null,
                SearchResult(result = listOf(0)),
                null,
                ReportResult(
                    result = listOf(
                        listOf(0, 4),
                        listOf(3, 3)
                    )
                )
            )
        )
    )

    override fun solve(testCaseInput: DesignMovieRentalSystemParams): List<DesignMovieRentalSystemResult?> {
        val movieRentingSystem = MovieRentingSystem(n = testCaseInput.n, entries = testCaseInput.entries)
        return testCaseInput.queries.map {
            when (it) {
                is Search -> SearchResult(
                    result = movieRentingSystem.search(movie = it.movie)
                )

                is Rent -> {
                    movieRentingSystem.rent(shop = it.shop, movie = it.movie)
                    null
                }

                is Drop -> {
                    movieRentingSystem.drop(shop = it.shop, movie = it.movie)
                    null
                }

                Report -> ReportResult(
                    result = movieRentingSystem.report()
                )
            }
        }
    }

    private class MovieRentingSystem(n: Int, entries: Array<IntArray>) {

        private val keyToItemMap = hashMapOf<Long, Item>()
        private val availableByMovieMap = hashMapOf<Int, TreeSet<Item>>()
        private val rentedSet = TreeSet<Item>()

        init {
            for ((shop, movie, price) in entries) {
                val item = Item(shop = shop, movie = movie, price = price)
                val key = key(shop = shop, movie = movie)
                keyToItemMap[key] = item
                availableByMovieMap.getOrPut(movie) { TreeSet() } += item
            }
        }

        fun search(movie: Int): List<Int> {
            val set = availableByMovieMap[movie]
            if (set.isNullOrEmpty()) return emptyList()

            return set.take(5).map { it.shop }
        }

        fun rent(shop: Int, movie: Int) {
            val key = key(shop = shop, movie = movie)
            val item = keyToItemMap[key] ?: return
            availableByMovieMap[movie]?.remove(item)
            rentedSet += item
        }

        fun drop(shop: Int, movie: Int) {
            val key = key(shop = shop, movie = movie)
            val item = keyToItemMap[key] ?: return
            rentedSet -= item
            availableByMovieMap.getOrPut(movie) { TreeSet() } += item
        }

        fun report(): List<List<Int>> {
            return rentedSet.take(5).map { listOf(it.shop, it.movie) }
        }

        private fun key(shop: Int, movie: Int): Long = (shop.toLong() shl 32) xor (movie.toUInt().toLong())

        private data class Item(
            val shop: Int,
            val movie: Int,
            val price: Int
        ) : Comparable<Item> {
            override fun compareTo(other: Item): Int = when {
                price != other.price -> price - other.price
                shop != other.shop -> shop - other.shop
                else -> movie - other.movie
            }
        }
    }

    data class DesignMovieRentalSystemParams(
        val n: Int,
        val entries: Array<IntArray>,
        val queries: List<DesignMovieRentalSystemType>
    ) {
        override fun toString(): String {
            return """
                
                n: $n
                entries: ${stringFromArray(entries)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as DesignMovieRentalSystemParams

            if (n != other.n) return false
            if (!entries.contentDeepEquals(other.entries)) return false
            if (queries != other.queries) return false

            return true
        }

        override fun hashCode(): Int {
            var result = n
            result = 31 * result + entries.contentDeepHashCode()
            result = 31 * result + queries.hashCode()
            return result
        }
    }

    sealed interface DesignMovieRentalSystemType {
        data class Search(val movie: Int) : DesignMovieRentalSystemType
        data class Rent(val shop: Int, val movie: Int) : DesignMovieRentalSystemType
        data class Drop(val shop: Int, val movie: Int) : DesignMovieRentalSystemType
        data object Report : DesignMovieRentalSystemType
    }

    sealed interface DesignMovieRentalSystemResult {
        data class SearchResult(val result: List<Int>) : DesignMovieRentalSystemResult
        data class ReportResult(val result: List<List<Int>>) : DesignMovieRentalSystemResult
    }
}
