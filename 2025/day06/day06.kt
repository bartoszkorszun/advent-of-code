import java.io.File

fun part2(grid: List<List<String>>): Int {
    return 0
}   

fun part1(grid: List<List<String>>): Long {
    var sum = 0L
    for (i in 0..grid.size - 1) {
        when (grid[i][grid[0].size-1]) {
            "*" -> { 
                var mulRes = 1L
                for (j in 0..grid[0].size-2) {
                    mulRes *= grid[i][j].toLong()
                }
                sum += mulRes
             }
            "+" -> { 
                var sumRes = 0L
                for (j in 0..grid[0].size-2) {
                    sumRes += grid[i][j].toLong()
                }
                sum += sumRes
             }
        }
    }
    return sum
}

fun extractProblems(grid: List<String>): List<List<String>> {
    val problems = mutableListOf<List<String>>()
    val rows = grid.size
    val cols = grid[0].length
    var c = 0

    while (c < cols) {
        while (c < cols && grid.all { it[c] == ' ' }) c++
        if (c >= cols) break

        val start = c
        while (c < cols && grid.any { it[c] != ' ' }) c++
        val end = c

        val block = grid.map { it.substring(start, end).trim() }
        problems += block
    }

    return problems
}

fun main() {
    val lines = File("test.txt").readLines()
    val width = lines.maxOf { it.length }
    val list = lines.map { it.padEnd(width, ' ') }

    val grid = extractProblems(list)
    
    val p1 = part1(grid)
    val p2 = part2(grid)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}