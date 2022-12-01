fun main() {

    fun sums(input: List<String>): List<Int> {
        val curSums = mutableListOf<Int>()
        var curSum = 0

        input.forEach {
            if (it.isEmpty()) {
                curSums.add(curSum)
                curSum = 0
            } else {
                curSum += it.toInt()
            }
        }

        return curSums
    }

    fun part1(input: List<String>): Int {
        return sums(input).max()
    }

    fun part2(input: List<String>): Int {
        return sums(input).sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val inputPart0 = readInput("Day01_part1", 1)
    println(part1(inputPart0))

    val inputPart1 = readInput("Day01_part2", 1)
    println(part2(inputPart1))
}
