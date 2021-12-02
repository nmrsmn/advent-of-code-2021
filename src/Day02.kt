fun main() {

    fun parse(line: String) : Instruction {
        val (command, step) = line.split(' ')
        return Instruction(
            command = Command.valueOf(command.uppercase()),
            step = step.toInt()
        )
    }

    fun part1(input: List<String>): Int {
        return input.map { parse(it) }.fold(initial = State(position = 0, depth = 0)) { state, instruction ->
            when (instruction.command) {
                Command.FORWARD -> State(state.position, state.depth + instruction.step)
                Command.DOWN -> State(state.position + instruction.step, state.depth)
                Command.UP -> State(state.position - instruction.step, state.depth)
            }
        }.let { it.position * it.depth }
    }

    fun part2(input: List<String>): Int {
        return input.map { parse(it) }.fold(initial = State(position = 0, depth = 0, aim = 0)) { state, instruction ->
            when (instruction.command) {
                Command.FORWARD -> State(
                    position = state.position + instruction.step,
                    depth = state.depth + (instruction.step * state.aim),
                    aim = state.aim
                )
                Command.DOWN -> State(state.position, state.depth, state.aim + instruction.step)
                Command.UP -> State(state.position, state.depth, state.aim - instruction.step)
            }
        }.let { it.position * it.depth }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

private data class Instruction(
    val command: Command,
    val step: Int
)

private data class State(
    val position: Int,
    val depth: Int,
    val aim: Int = 0
)

private enum class Command {
    FORWARD,
    DOWN,
    UP
}