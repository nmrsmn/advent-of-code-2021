fun main() {
    fun part1(input: List<String>): Int {
        return input.map(String::toInt)
            .zipWithNext()
            .fold(initial = 0) { accumulator, pair ->
                accumulator + if (pair.first < pair.second) 1 else 0
            }
    }

    fun part2(input: List<String>): Int {
        return input.map(String::toInt)
            .windowed(size = 3, step = 1)
            .map { it.sum() }
            .zipWithNext()
            .fold(initial = 0) { accumulator, pair ->
                accumulator + if (pair.first < pair.second) 1 else 0
            }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
