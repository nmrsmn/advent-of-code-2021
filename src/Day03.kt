fun main() {
    fun List<String>.commonBit(position: Int): Boolean
        = 2 * count { it[position] == '1' } >= size

    fun List<String>.filterByCommonBit(position: Int, invert: Boolean) = when (size) {
        1 -> this
        else -> this.filter { (it[position] == this.commonBit(position).char) xor invert }
    }

    fun part1(input: List<String>): Int {
        val mask = "1".repeat(input.first().length).toInt(2)
        val gamma = input.first().indices.joinToString(separator = "") { position ->
            input.commonBit(position).string
        }.toInt(2)

        return gamma * (gamma xor mask)
    }

    fun part2(input: List<String>): Int {
        val oxygen = input.first().indices.fold(input) { accumulator, position -> accumulator.filterByCommonBit(position, false) }
        val co2 = input.first().indices.fold(input) { accumulator, position -> accumulator.filterByCommonBit(position, true) }

        return oxygen.single().toInt(2) * co2.single().toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
