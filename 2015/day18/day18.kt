import java.io.File

fun List<List<Boolean>>.count(): Int {
    return this.sumOf { row -> row.count { it } }
}

fun parseInput(input: String): List<List<Boolean>> {
    return input.lines().map { row ->
        row.map { it == '#' }
    }
}

fun part2(input: List<List<Boolean>>): Int {
    var grid = input.map { it.toMutableList() }.toMutableList()
    repeat(100) {
        var newGrid: MutableList<MutableList<Boolean>> = mutableListOf()
        for (y in grid.indices) {
            var newRow = mutableListOf<Boolean>()
            for (x in grid[y].indices) {
                var count = 0
                for (dy in -1..1) {
                    for (dx in -1..1) {
                        if (dy == 0 && dx == 0) continue
                        val ny = y + dy
                        val nx = x + dx
                        if (ny < 0 || ny >= grid.size || nx < 0 || nx >= grid[y].size) continue
                        if (grid[ny][nx]) count++
                    }
                }
                newRow.add(grid[y][x] && count in 2..3 || !grid[y][x] && count == 3)
            }
            newGrid.add(newRow)
        }
        grid = newGrid
    }
    return grid.count()
}   

fun part1(input: List<List<Boolean>>): Int {

    return 0
}

fun main() {
    val input = File("input.txt").readText().trim()
    val grid = parseInput(input)
    
    val p1 = part1(grid)
    val p2 = part2(grid)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}