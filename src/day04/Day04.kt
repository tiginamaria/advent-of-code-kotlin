package day04

import readInput

fun main() {

    fun part1(input: List<String>) = input.count {
        val (a, b, c, d) = it.split(',')
            .flatMap { range -> range.split("-").map { border -> border.toInt() } }

        (a >= c && b <= d) || (a <= c && b >= d)
    }

    fun part2(input: List<String>) = input.count {
        val (a, b, c, d) = it.split(',')
            .flatMap { range -> range.split("-").map { border -> border.toInt() } }

        (c <= b && a <= d)
    }

    val input = readInput("Day04", 4)

    println(part1(input))
    println(part2(input))
}
