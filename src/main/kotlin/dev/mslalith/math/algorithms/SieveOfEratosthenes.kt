package dev.mslalith.math.algorithms

/**
 * Generate primes using Sieve of Eratosthenes algorithm
 *
 * Time Complexity: O(n * log(log(n)))
 */
fun generatePrimesTill(n: Int): BooleanArray {
    val primes = BooleanArray(n + 1) { true }
    primes[0] = false
    primes[1] = false

    var i = 2
    while (i * i <= n) {
        if (primes[i]) {

            // mark multiples of i as not prime
            var j = i * i
            while (j <= n) {
                primes[j] = false
                j += i
            }
        }
        i++
    }

    return primes
}

fun generatePrimesTillAsList(n: Int): List<Int> {
    val primes = BooleanArray(n + 1) { true }
    primes[0] = false
    primes[1] = false

    var i = 2
    while (i * i <= n) {
        if (primes[i]) {

            // mark multiples of i as not prime
            var j = i * i
            while (j <= n) {
                primes[j] = false
                j += i
            }
        }
        i++
    }

    return primes.indices.filter { primes[it] }
}
