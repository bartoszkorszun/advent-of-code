import java.io.File

fun part1(input: CharArray): Int {
    var floor = 0
    for (c in input) {
        if (c.code == 40) {
            floor++
        } 
        if (c.code == 41) {
            floor--
        }
    }
    return floor
}

fun part2(input: CharArray): Int {
    var floor = 0
    var position = 1
    for (c in input) {
        if (c.code == 40) {
            floor++
        } 
        if (c.code == 41) {
            floor--
        }
        if (floor == -1) {
            return position
        }
        position++
    }
    return -1
}

fun main() {
    val input = File("input.txt").readText()
    input.toCharArray()

    val p1 = part1(input.toCharArray())
    val p2 = part2(input.toCharArray())

    println("Part 1: $p1")
    println("Part 2: $p2")
}