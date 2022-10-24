package utils

/**
 * Builders
 */
fun buildArray(input: String): Array<String> = buildList(input).toTypedArray()

fun buildList(input: String): List<String> = buildList(input) { it }

fun <T> buildList(input: String, transform: (String) -> T): List<T> {
    return input.split(" ").map(transform)
}

/**
 * Display
 */
fun stringFromArray(array: IntArray): String = stringFromArray(array.toTypedArray())
fun stringFromArray(array: Array<String>): String = array.joinToString(prefix = "[", postfix = "]")

fun <T> stringFromArray(array: Array<T>): String = array.joinToString(prefix = "[", postfix = "]")
