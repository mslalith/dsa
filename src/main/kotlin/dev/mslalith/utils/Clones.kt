package dev.mslalith.utils

inline fun <reified T : Cloneable> Array<T>.createClone(): Array<T> = when (T::class) {
    IntArray::class -> Array(size) { (get(it) as IntArray).clone() as T }
    else -> throw UnsupportedOperationException("Not handled for ${T::class.simpleName} type")
}
