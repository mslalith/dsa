package dev.mslalith.math.algorithms

/**
 * Generate primes using Sieve of Eratosthenes algorithm
 *
 * Time Complexity: O(n * log(log(n)))
 */
fun generatePrimesTill(n: Int): List<Int> = buildList {
    val primes = IntArray(n + 1) { 1 }
    primes[0] = 0
    primes[1] = 0

    var i = 2
    while (i * i <= n) {
        if (primes[i] == 1) {
            add(i)

            // mark multiples of i as not prime
            var j = i * i
            while (j <= n) {
                primes[j] = 0
                j += i
            }
        }
        i++
    }
}
