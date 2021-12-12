fun main() {
    fun part1(input: List<String>): Int {
        val numbers = input.first().split(",").map(String::toInt)
        val boards = input.drop(1).filter(String::isNotEmpty).chunked(5).map { row ->
            Board(row.map { column -> column.split(" ").filter(String::isNotEmpty).map(String::toInt) })
        }

        numbers.indices.forEach { offset ->
            val drawn = numbers.take(offset + 1)
            boards.firstOrNull { it.isValidBingo(drawn) }?.let { board ->
                return@part1 board.sumOfUnmarkedNumbers(drawn) * drawn.last()
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        val numbers = input.first().split(",").map(String::toInt)
        val boards = input.drop(1).filter(String::isNotEmpty).chunked(5).map { row ->
            Board(row.map { column -> column.split(" ").filter(String::isNotEmpty).map(String::toInt) })
        }

        val completed = mutableListOf<IndexedValue<Board>>()
        numbers.indices.forEach { offset ->
            val drawn = numbers.take(offset + 1)
            completed += boards.withIndex().filter { it.index !in completed.map { it.index } && it.value.isValidBingo(drawn) }
            if (completed.size == boards.size) {
                return@part2 completed.last().value.sumOfUnmarkedNumbers(drawn) * drawn.last()
            }
        }

        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

private data class Board(val rows: List<List<Int>>) {

    fun sumOfUnmarkedNumbers(drawn: List<Int>) : Int
        = rows.flatten().filter { it !in drawn }.sum()

    fun isValidBingo(drawn: List<Int>) : Boolean
        = hasRowBingo(drawn) || hasColumnBingo(drawn)

    private fun hasRowBingo(drawn: List<Int>)
        = rows.isDrawn(drawn)

    private fun hasColumnBingo(drawn: List<Int>)
        = (0 .. 4).map { column -> rows.map { it[column] } }.isDrawn(drawn)

    private fun List<List<Int>>.isDrawn(drawn: List<Int>)
        = any { list -> list.all { it in drawn } }
}