import kotlin.math.abs

fun main() {
    fun determineLinePoints(a: Point, b: Point) : List<Point> = a.steps(b).map { step ->
        Point(
            x = if (b.x > a.x) a.x + step else if(b.x < a.x) a.x - step else a.x,
            y = if (b.y > a.y) a.y + step else if(b.y < a.y) a.y - step else a.y
        )
    }

    fun part1(input: List<String>): Int {
        return input
            .map { line ->
                line.split(" -> ").map { point ->
                    point.split(",").map(String::toInt).let { (x, y) ->
                        Point(x, y)
                    }
                }
            }
            .filter { (a, b) -> a.x == b.x || a.y == b.y }
            .flatMap { (a, b) -> determineLinePoints(a, b) }
            .groupBy { it }
            .count { it.value.size > 1 }
    }

    fun part2(input: List<String>): Int {
        val a= input
            .map { line ->
                line.split(" -> ").map { point ->
                    point.split(",").map(String::toInt).let { (x, y) ->
                        Point(x, y)
                    }
                }
            }
//            .filter { (a, b) -> abs(a.x - b.x) < 2 && abs(a.y - b.y) < 2 }
            .flatMap { (a, b) -> determineLinePoints(a, b) }
            .groupBy { it }
            .count { it.value.size > 1 }

        return a
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}