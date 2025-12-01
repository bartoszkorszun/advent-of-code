import java.io.File

fun parseInput(input: String): List<Pair<String, Int>> {
    return input
        .lines()
        .filter { it.isNotBlank() }
        .map { line ->
            val dir = line.first().toString()
            val num = line.drop(1).toInt()
            dir to num
        }
}

fun move(instruction: Pair<String, Int>, dialPoint: Int): Pair<Boolean, Int> {
    when (instruction.first) {
        "L" -> {
            var sum = dialPoint - (instruction.second % 100)
            if (sum < 0) sum += 100
            return Pair(sum == 0, sum)
        }
        "R" -> {
            var sum = dialPoint + (instruction.second % 100)
            if (sum > 99) sum -= 100
            return Pair(sum == 0, sum)
        }
    }
    return Pair(false, 0)
}

fun part2(instructions: List<Pair<String, Int>>): Int {
    return 0
}   

fun part1(instructions: List<Pair<String, Int>>): Int {
    var dialPoint = 50
    var zeroCount = 0
    for (instruction in instructions) {
        val (isZero, newPoint) = move(instruction, dialPoint)
        if(isZero) zeroCount++
        dialPoint = newPoint
    }

    return zeroCount
}

fun main() {
    val input = File("input.txt").readText().trim()
    val instructions = parseInput(input)
    
    val p1 = part1(instructions)
    val p2 = part2(instructions)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}