import java.io.File

data class Command(
    val action: String, 
    val start: Pair<Int, Int>, 
    val end: Pair<Int, Int>
)

fun parseInput(input: String): List<Command> {
    val regex = Regex("(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)")
    return input.lines().mapNotNull { line ->
        regex.matchEntire(line)?.destructured?.let { (action, x1, y1, x2, y2) ->
            Command(
                action, 
                Pair(x1.toInt(), y1.toInt()), 
                Pair(x2.toInt(), y2.toInt()))
        }
    }
}

fun part2(input: String): Int {
    val commands = parseInput(input)
    val grid = Array(1000) { IntArray(1000) }

    for (command in commands) {
        for (x in command.start.first..command.end.first) {
            for (y in command.start.second..command.end.second) {
                when (command.action) {
                    "turn on" -> grid[x][y]++
                    "turn off" -> grid[x][y] = maxOf(0, grid[x][y] - 1)
                    "toggle" -> grid[x][y] += 2
                }
            }
        }
    }

    return grid.sumOf { it.sum() }
}

fun part1(input: String): Int {
    val commands = parseInput(input)
    val grid = Array(1000) { BooleanArray(1000) }

    for (command in commands) {
        for (x in command.start.first..command.end.first) {
            for (y in command.start.second..command.end.second) {
                when (command.action) {
                    "turn on" -> grid[x][y] = true
                    "turn off" -> grid[x][y] = false
                    "toggle" -> grid[x][y] = !grid[x][y]
                }
            }
        }
    }

    return grid.sumOf { it.count { it } }
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}