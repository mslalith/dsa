package src.utils

/**
 * Builders
 */
fun buildArray(input: String, separator: String = ","): Array<String> = buildList(input, separator).toTypedArray()

fun buildIntArray(input: String, separator: String = ","): IntArray = buildList(
    input = input,
    separator = separator
) { it.toInt() }.toIntArray()

fun buildList(input: String, separator: String = ","): List<String> = buildList(input, separator) { it }

fun <T> buildList(input: String, separator: String, transform: (String) -> T): List<T> {
    if (input.isEmpty()) return emptyList()
    return input.split(separator).map(transform)
}

/**
 * Display
 */
fun stringFromArray(array: Array<IntArray>): String = array.joinToString(prefix = "[", postfix = "]") { stringFromArray(it) }
fun stringFromArray(array: Array<CharArray>): String = array.joinToString(prefix = "[", postfix = "]") { stringFromArray(it) }

fun stringFromArray(array: IntArray): String = stringFromArray(array.toTypedArray())
fun stringFromArray(array: DoubleArray): String = stringFromArray(array.toTypedArray())
fun stringFromArray(array: CharArray): String = stringFromArray(array.toTypedArray())

fun <T> stringFromArray(array: Array<T>): String = array.joinToString(prefix = "[", postfix = "]", separator = ", ")

/**
 * Helpers
 */
fun <T> List<T>.unOrderEquals(other: List<T>): Boolean {
    if (this.size != other.size) return false

    val set = hashSetOf<T>()

    for (item in this) {
        set.add(item)
    }

    for (item in other) {
        val match = set.firstOrNull {
            when (it) {
                is List<*> -> it.unOrderEquals(item as List<*>)
                else -> it == item
            }
        }
        if (!set.contains(match)) return false
        if (match == null) return false
        set.remove(match)
    }

    return set.isEmpty()
}