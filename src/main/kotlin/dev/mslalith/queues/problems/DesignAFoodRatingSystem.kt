package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.queues.problems.DesignAFoodRatingSystem.DesignAFoodRatingSystemParams
import dev.mslalith.queues.problems.DesignAFoodRatingSystem.DesignAFoodRatingSystemType.ChangeRating
import dev.mslalith.queues.problems.DesignAFoodRatingSystem.DesignAFoodRatingSystemType.HighestRated
import dev.mslalith.utils.stringFromArray
import java.util.*


class DesignAFoodRatingSystem : TestCaseProblem<DesignAFoodRatingSystemParams, Array<String?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DesignAFoodRatingSystem().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<DesignAFoodRatingSystemParams, Array<String?>>> = arrayOf(
        TestCase(
            input = DesignAFoodRatingSystemParams(
                foods = arrayOf("kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"),
                cuisines = arrayOf("korean", "japanese", "japanese", "greek", "japanese", "korean"),
                ratings = intArrayOf(9, 12, 8, 15, 14, 7),
                queries = listOf(
                    HighestRated(cuisine = "korean"),
                    HighestRated(cuisine = "japanese"),
                    ChangeRating(food = "sushi", rating = 16),
                    HighestRated(cuisine = "japanese"),
                    ChangeRating(food = "ramen", rating = 16),
                    HighestRated(cuisine = "japanese")
                )
            ),
            output = arrayOf("kimchi", "ramen", null, "sushi", null, "ramen")
        ),
        TestCase(
            input = DesignAFoodRatingSystemParams(
                foods = arrayOf("tjokfmxg", "xmiuwozpmj", "uqklk", "mnij", "iwntdyqxi", "cduc", "cm", "mzwfjk"),
                cuisines = arrayOf("waxlau", "ldpiabqb", "ldpiabqb", "waxlau", "ldpiabqb", "waxlau", "waxlau", "waxlau"),
                ratings = intArrayOf(9, 13, 7, 16, 10, 17, 16, 17),
                queries = listOf(
                    ChangeRating(food = "tjokfmxg", rating = 19),
                    HighestRated(cuisine = "waxlau"),
                    ChangeRating(food = "uqklk", rating = 7),
                    HighestRated(cuisine = "waxlau"),
                    HighestRated(cuisine = "waxlau"),
                    ChangeRating(food = "tjokfmxg", rating = 14),
                    HighestRated(cuisine = "waxlau"),
                    HighestRated(cuisine = "waxlau"),
                    ChangeRating(food = "tjokfmxg", rating = 4),
                    HighestRated(cuisine = "waxlau"),
                    ChangeRating(food = "mnij", rating = 18),
                    HighestRated(cuisine = "waxlau")
                )
            ),
            output = arrayOf(null, "tjokfmxg", null, "tjokfmxg", "tjokfmxg", null, "cduc", "cduc", null, "cduc", null, "mnij")
        )
    )

    override fun solve(testCaseInput: DesignAFoodRatingSystemParams): Array<String?> {
        val foodRatings = FoodRatings(foods = testCaseInput.foods, cuisines = testCaseInput.cuisines, ratings = testCaseInput.ratings)
        return testCaseInput.queries.map {
            when (it) {
                is HighestRated -> foodRatings.highestRated(cuisine = it.cuisine)
                is ChangeRating -> {
                    foodRatings.changeRating(food = it.food, newRating = it.rating)
                    null
                }
            }
        }.toTypedArray()
    }

    private class FoodRatings(
        foods: Array<String>,
        cuisines: Array<String>,
        ratings: IntArray
    ) {
        private val foodToFoodMap = hashMapOf<String, Food>()
        private val foodToRatingMap = hashMapOf<String, Int>()
        private val cuisineToFoodMap = hashMapOf<String, PriorityQueue<Food>>()

        init {
            for (i in foods.indices) {
                val food = Food(name = foods[i], cuisine = cuisines[i], rating = ratings[i])
                foodToFoodMap[foods[i]] = food
                foodToRatingMap[foods[i]] = ratings[i]
                cuisineToFoodMap
                    .computeIfAbsent(cuisines[i]) { PriorityQueue<Food>() }
                    .add(food)
            }
        }

        fun changeRating(food: String, newRating: Int) {
            foodToRatingMap[food] = newRating
            val food = foodToFoodMap[food]!!
            cuisineToFoodMap[food.cuisine]!!.add(food.copy(rating = newRating))
        }

        fun highestRated(cuisine: String): String {
            val pq = cuisineToFoodMap[cuisine]!!
            var highestRated = pq.peek()

            while (highestRated.rating != foodToRatingMap[highestRated.name]) {
                pq.poll()
                highestRated = pq.peek()
            }

            return highestRated.name
        }

        private data class Food(
            val name: String,
            val cuisine: String,
            val rating: Int
        ) : Comparable<Food> {
            override fun compareTo(other: Food): Int = when {
                rating != other.rating -> other.rating.compareTo(rating)
                else -> name.compareTo(other.name)
            }
        }
    }

    data class DesignAFoodRatingSystemParams(
        val foods: Array<String>,
        val cuisines: Array<String>,
        val ratings: IntArray,
        val queries: List<DesignAFoodRatingSystemType>
    ) {
        override fun toString(): String {
            return """
                
                foods: ${stringFromArray(foods)}
                cuisines: ${stringFromArray(cuisines)}
                ratings: ${stringFromArray(ratings)}
                queries: $queries
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as DesignAFoodRatingSystemParams

            if (!foods.contentEquals(other.foods)) return false
            if (!cuisines.contentEquals(other.cuisines)) return false
            if (!ratings.contentEquals(other.ratings)) return false
            if (queries != other.queries) return false

            return true
        }

        override fun hashCode(): Int {
            var result = foods.contentHashCode()
            result = 31 * result + cuisines.contentHashCode()
            result = 31 * result + ratings.contentHashCode()
            result = 31 * result + queries.hashCode()
            return result
        }
    }

    sealed interface DesignAFoodRatingSystemType {
        data class HighestRated(
            val cuisine: String
        ) : DesignAFoodRatingSystemType

        data class ChangeRating(
            val food: String,
            val rating: Int
        ) : DesignAFoodRatingSystemType
    }
}
