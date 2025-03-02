import java.io.File

fun part2(containers: List<Int>, liters: Int): Int {
    return 0
}   

fun part1(containers: List<Int>, liters: Int): Int {
    val result = mutableListOf<List<Int>>()

    fun backtrack(start: Int, sum: Int, combination: MutableList<Int>) {
        if (sum == liters) {
            result.add(combination.toList())
            return
        }

        if (sum > liters || start >= containers.size) {
            return
        }

        combination.add(containers[start])
        backtrack(start + 1, sum + containers[start], combination)
        combination.removeAt(combination.size - 1)
        backtrack(start + 1, sum, combination)
    }

    backtrack(0, 0, mutableListOf())
    return result.size
}

fun main() {
    val input = File("input.txt").readText().trim()
    val containers = input.lines().map { it.toInt() }
    val liters = 150

    val p1 = part1(containers, liters)
    val p2 = part2(containers, liters)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}