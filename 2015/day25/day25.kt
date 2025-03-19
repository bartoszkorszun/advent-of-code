import java.io.File

fun part2(input: Long, row: Int, col: Int): Long {
    println("There is no part 2 for this day")
    return 0
}   

fun part1(input: Long, row: Int, col: Int): Long {
    var i = 1
    var j = 1
    var code = input

    while (i != row || j != col) {
        if (i == 1) {
            i = j + 1
            j = 1
        } else {
            i--
            j++
        }
        code = (code * 252533) % 33554393
    }

    return code
}

fun main() {
    val input = File("input.txt").readText().trim()
    val row = 2981
    val col = 3075

    val p1 = part1(input.toLong(), row, col)
    val p2 = part2(input.toLong(), row, col)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}