package day03

import readInput

private fun priority(c: Char): Int {
    if (c.isLowerCase()) return c - 'a' + 1
    return c - 'A' + 27
}

fun main() {
    fun part1(input: List<String>) = input.sumOf {
        val n = it.length / 2
        val firstComp = it.take(n).toSet()
        val lastComp = it.takeLast(n).toSet()

        firstComp.intersect(lastComp).sumOf { shareComp -> priority(shareComp) }
    }

    fun part2(input: List<String>) =
        input.chunked(3).sumOf { group ->
            group[0].toSet().intersect(group[1].toSet()).intersect(group[2].toSet()).sumOf { priority(it) }
        }

    val input = readInput("Day03", 3)

    println(part1(input))
    println(part2(input))
}
