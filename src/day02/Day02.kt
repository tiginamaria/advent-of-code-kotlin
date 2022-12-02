package day02

import readInput

enum class Shape(val score: Int) {
    Rock(1), Paper(2), Scissors(3);

    fun compare(opponent: Shape): Result {
        if (this.score == opponent.score) return Result.Draw
        if ((this.score + 1) % 3 == opponent.score % 3) return Result.Lose

        return Result.Win
    }



    fun pair(result: Result): Shape {
        if (result == Result.Draw) return this
        if (result == Result.Lose) return Shape.values()[(this.ordinal - 1 + 3) % 3]

        return Shape.values()[(this.ordinal + 1) % 3]
    }
}

enum class Result(val score: Int) {
    Win(6), Draw(3), Lose(0);
}

fun main() {

    val opponentToShape = mapOf(
        'A' to Shape.Rock,
        'B' to Shape.Paper,
        'C' to Shape.Scissors,
    )

    val myToShape = mapOf(
        'X' to Shape.Rock,
        'Y' to Shape.Paper,
        'Z' to Shape.Scissors,
    )

    val myToResult = mapOf(
        'X' to Result.Lose,
        'Y' to Result.Draw,
        'Z' to Result.Win,
    )

    fun part1(input: List<String>): Int {

        var totalScore = 0

        input.forEach {
            val opponentShape = opponentToShape[it[0]]!!
            val myShape = myToShape[it[2]]!!

            val myResult = myShape.compare(opponentShape)
            totalScore += myResult.score + myShape.score
        }

        return totalScore
    }

    fun part2(input: List<String>): Int {

        var totalScore = 0

        input.forEach {
            val opponentShape = opponentToShape[it[0]]!!
            val myResult = myToResult[it[2]]!!

            val myShape = opponentShape.pair(myResult)
            totalScore += myResult.score + myShape.score
        }

        return totalScore
    }


    val input = readInput("Day02", 2)
    println(part1(input))
    println(part2(input))

}
