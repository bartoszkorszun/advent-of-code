import java.io.File

fun part2(input: String, keypad: Array<Array<Int>>): Int {
    return 0
}   

fun part1(input: String, keypad: Array<Array<Int>>): Int {
    val lines = input.lines().map { it.toCharArray() }
    var code = 0
    var x = 1
    var y = 1
    for (line in lines) {
        for (c in line) {
            when (c) {
                'U' -> y = maxOf(0, y - 1)
                'D' -> y = minOf(2, y + 1)
                'L' -> x = maxOf(0, x - 1)
                'R' -> x = minOf(2, x + 1)
            }
        }
        code = code * 10 + keypad[y][x]
    }
    return code
}

fun main() {
    val input = File("input.txt").readText().trim()
    val keypad = arrayOf(
        arrayOf(1, 2, 3),
        arrayOf(4, 5, 6),
        arrayOf(7, 8, 9)
    )
    
    val p1 = part1(input, keypad)
    val p2 = part2(input, keypad)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}