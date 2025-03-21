import java.io.File

fun part2(input: String): String {
    val keypad = arrayOf(
        arrayOf('0', '0', '1', '0', '0'),
        arrayOf('0', '2', '3', '4', '0'),
        arrayOf('5', '6', '7', '8', '9'),
        arrayOf('0', 'A', 'B', 'C', '0'),
        arrayOf('0', '0', 'D', '0', '0')
    )
    val lines = input.lines().map { it.toCharArray() }
    var code: StringBuilder = StringBuilder()
    var x = 0
    var y = 2
    for (line in lines) {
        for (c in line) {
            when (c) {
                'U' -> if (y > 0 && keypad[y - 1][x] != '0') y--
                'D' -> if (y < 4 && keypad[y + 1][x] != '0') y++
                'L' -> if (x > 0 && keypad[y][x - 1] != '0') x--
                'R' -> if (x < 4 && keypad[y][x + 1] != '0') x++
            }
        }
        code.append(keypad[y][x].toString())
    }
    return code.toString()
}   

fun part1(input: String): Int {
    val keypad = arrayOf(
        arrayOf(1, 2, 3),
        arrayOf(4, 5, 6),
        arrayOf(7, 8, 9)
    )
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
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}