package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.FindAllPossibleRecipesFromGivenSupplies.FindAllPossibleRecipesFromGivenSuppliesParams
import dev.mslalith.utils.stringFromArray

class FindAllPossibleRecipesFromGivenSupplies : TestCaseProblem<FindAllPossibleRecipesFromGivenSuppliesParams, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindAllPossibleRecipesFromGivenSupplies().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<FindAllPossibleRecipesFromGivenSuppliesParams, List<String>>> = arrayOf(
        TestCase(
            input = FindAllPossibleRecipesFromGivenSuppliesParams(
                recipes = arrayOf("bread"),
                ingredients = listOf(
                    listOf("yeast", "flour")
                ),
                supplies = arrayOf("yeast", "flour", "corn")
            ),
            output = listOf("bread")
        ),
        TestCase(
            input = FindAllPossibleRecipesFromGivenSuppliesParams(
                recipes = arrayOf("bread", "sandwich"),
                ingredients = listOf(
                    listOf("yeast", "flour"),
                    listOf("bread", "meat")
                ),
                supplies = arrayOf("yeast", "flour", "meat")
            ),
            output = listOf("bread", "sandwich")
        ),
        TestCase(
            input = FindAllPossibleRecipesFromGivenSuppliesParams(
                recipes = arrayOf("bread", "sandwich", "burger"),
                ingredients = listOf(
                    listOf("yeast", "flour"),
                    listOf("bread", "meat"),
                    listOf("sandwich", "meat", "bread")
                ),
                supplies = arrayOf("yeast", "flour", "meat")
            ),
            output = listOf("bread", "sandwich", "burger")
        )
    )

    override fun solve(testCaseInput: FindAllPossibleRecipesFromGivenSuppliesParams): List<String> {
        return findAllRecipes(testCaseInput.recipes, testCaseInput.ingredients, testCaseInput.supplies)
    }

    private fun findAllRecipes(
        recipes: Array<String>,
        ingredients: List<List<String>>,
        supplies: Array<String>
    ): List<String> {
        val recipeMap = hashMapOf<String, List<String>>()
        for (i in recipes.indices) recipeMap[recipes[i]] = ingredients[i]

        val memo = hashMapOf<String, Boolean>()
        for (supply in supplies) memo[supply] = true

        val visited = hashSetOf<String>()

        fun canCook(recipe: String): Boolean {
            if (recipe in memo) return memo.getValue(recipe)

            if (recipe in visited) return false
            if (recipe !in recipeMap) return false

            visited += recipe

            for (ingredient in recipeMap[recipe]!!) {
                // we have in supply, so can be prepared
                if (memo[ingredient] == true) continue

                // not in supply, that means it must be a recipe
                // check if the recipe can be cooked
                if (ingredient in recipeMap && canCook(ingredient)) continue

                // cannot cook
                visited -= recipe
                memo[recipe] = false
                return false
            }

            memo[recipe] = true
            visited -= recipe
            return true
        }

        return recipes.filter { canCook(it) }
    }

    data class FindAllPossibleRecipesFromGivenSuppliesParams(
        val recipes: Array<String>,
        val ingredients: List<List<String>>,
        val supplies: Array<String>
    ) {
        override fun toString(): String = """
                
                recipes: ${stringFromArray(recipes)}
                ingredients: $ingredients
                supplies: ${stringFromArray(supplies)}
            """.trimIndent()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FindAllPossibleRecipesFromGivenSuppliesParams

            if (!recipes.contentEquals(other.recipes)) return false
            if (ingredients != other.ingredients) return false
            if (!supplies.contentEquals(other.supplies)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = recipes.contentHashCode()
            result = 31 * result + ingredients.hashCode()
            result = 31 * result + supplies.contentHashCode()
            return result
        }
    }
}
