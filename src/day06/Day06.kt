package day06

import readInput


fun main() {


    fun part1(input: String, k: Int): Int {
        val s = mutableMapOf<Char, Int>()
        input.forEachIndexed { i, c ->
            if (i >= k) {
                val r = input[i - k]
                val rCount = s[r] ?: error("Wrong set content")
                if (rCount == 1) {
                    s.remove(r)
                } else {
                    s[r] = rCount - 1
                }
            }

            val cCount = s.getOrDefault(c, 0)
            s[c] = cCount + 1

            if (s.size == k) {
                return@part1 i + 1
            }
        }
        return -1
    }

    val input = readInput("Day06", 6).first()

    println(part1(input, 4))
    println(part1(input, 14))
}
