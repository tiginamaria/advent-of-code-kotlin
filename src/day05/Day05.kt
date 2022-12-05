package day05

import readInput
import java.lang.Integer.min
import java.util.Stack

data class Move(val count: Int, val from: Int, val to: Int)

fun main() {

    fun parseStacks(stacksInput: List<String>, stacksNums: String): List<Stack<Char>> {

        val positions = (1..stacksNums.length step 4)
        val stacks = positions.map { Stack<Char>() }.toList()

        stacksInput.reversed().forEach { level ->
            positions.forEachIndexed { i, p ->
                if (p < level.length && level[p].isLetter()) {
                    stacks[i].add(level[p])
                }
            }
        }

        return stacks
    }

    fun parseMoves(moveInput: List<String>): List<Move> {
        val moveRegex = Regex("move ([0-9]+) from ([0-9]+) to ([0-9]+)")
        return moveInput.map {
            val (count, from, to) = moveRegex.findAll(it).first().groupValues.drop(1).map { it.toInt() }
            Move(count, from - 1, to - 1)
        }
    }

    fun applyMoves(stacks: List<Stack<Char>>, moves: List<Move>) {
        moves.forEach { move ->
            (1..min(move.count, stacks[move.from].size)).forEach {
                stacks[move.to].push(stacks[move.from].pop())
            }
        }
    }

    fun applyMultiMoves(stacks: List<Stack<Char>>, moves: List<Move>) {
        moves.forEach { move ->
            (1..min(move.count, stacks[move.from].size)).map {
                stacks[move.from].pop()
            }.reversed().forEach { stacks[move.to].push(it) }
        }
    }

    fun part1(input: List<String>): String {
        val stacksInput = input.takeWhile { !it.startsWith(" 1") }
        val stacksNums = input.findLast { it.startsWith(" 1") }!!
        val stacksMoves = input.takeLastWhile { it.startsWith('m') }

        val stacks = parseStacks(stacksInput, stacksNums)
        val moves = parseMoves(stacksMoves)

        applyMoves(stacks, moves)

        return stacks.map { it.peek() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val stacksInput = input.takeWhile { !it.startsWith(" 1") }
        val stacksNums = input.findLast { it.startsWith(" 1") }!!
        val stacksMoves = input.takeLastWhile { it.startsWith('m') }

        val stacks = parseStacks(stacksInput, stacksNums)
        val moves = parseMoves(stacksMoves)

        applyMultiMoves(stacks, moves)

        return stacks.map { it.peek() }.joinToString("")
    }

    val input = readInput("Day05", 5)

    println(part1(input))
    println(part2(input))
}
