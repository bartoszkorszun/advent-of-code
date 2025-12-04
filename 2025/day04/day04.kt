import java.io.File

fun checkIfAccessible(grid: List<List<Boolean>>, positionYX: Pair<Int, Int>): Boolean {
    var sum = 0
    for (i in -1..1) {
        for (j in -1..1) {
            if (
                positionYX.first + i < 0 ||
                positionYX.first + i > grid.size-1 ||
                positionYX.second + j < 0 ||
                positionYX.second + j > grid[0].size-1
            ) continue
            if (grid[positionYX.first + i][positionYX.second + j]) sum++
        }
        if (sum > 4) return false
    }
    return true
}

fun part2(grid: List<List<Boolean>>): Int {
    val mutableGrid = grid.map { it.toMutableList() }.toMutableList()
    var sum = 0
    var y = 0
    var incFlag = true
    while (y < grid.size) {
        incFlag = true
        for (x in 0..grid[0].size-1) {
            if (!mutableGrid[y][x]) continue
            if (checkIfAccessible(mutableGrid, Pair(y,x))) {
                sum++
                mutableGrid[y][x] = false
                y = 0
                incFlag = false
            }
        }
        if (incFlag) y++
    }
    return sum
}   

fun part1(grid: List<List<Boolean>>): Int {
    var sum = 0
    for (i in 0..grid.size-1) {
        for (j in 0..grid[0].size-1) {
            if (!grid[i][j]) continue
            if (checkIfAccessible(grid, Pair(i, j))) sum++        
        }
    }
    return sum
}

fun parseInput(input: List<String>): List<List<Boolean>> {
    val cols = input[0].length
    return input.map { row -> 
        row.map { ch -> ch == '@' }
    }
}

fun main() {
    val input = File("input.txt").readText().trim().lines()
    val grid = parseInput(input)
    
    val p1 = part1(grid)
    val p2 = part2(grid)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}