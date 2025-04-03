import java.io.File

fun drawRect(grid: Array<IntArray>, width: Int, height: Int) {
    for (y in 0 until height) {
        for (x in 0 until width) {
            grid[y][x] = 1
        }
    }
}

fun rotateRow(grid: Array<IntArray>, row: Int, amount: Int) {
    val temp = grid[row].copyOf()
    for (x in grid[row].indices) {
        grid[row][(x + amount) % grid[row].size] = temp[x]
    }
}

fun rotateColumn(grid: Array<IntArray>, column: Int, amount: Int) {
    val temp = IntArray(grid.size)
    for (y in grid.indices) {
        temp[y] = grid[y][column]
    }
    for (y in grid.indices) {
        grid[(y + amount) % grid.size][column] = temp[y]
    }
}

fun part2(grid: Array<IntArray>) {
    for (row in grid) {
        println(row.joinToString("") { if (it == 1) "#" else "." })
    }
}   

fun part1(grid: Array<IntArray>, instructions: List<Instruction>): Pair<Int, Array<IntArray>> {
    for (instruction in instructions) {
        when (instruction) {
            is Instruction.Rect -> drawRect(grid, instruction.width, instruction.height)
            is Instruction.RotateRow -> rotateRow(grid, instruction.row, instruction.amount)
            is Instruction.RotateColumn -> rotateColumn(grid, instruction.column, instruction.amount)
        }
    }
    val result = grid.sumOf { row -> row.count { it == 1 } }
    return Pair(result, grid)
}

fun main() {
    val input = File("input.txt").readText().trim()
    val instructions = parseInput(input)

    val grid = Array(6) { IntArray(50) { 0 } }

    val p1 = part1(grid, instructions)

    println("Part 1: ${p1.first}")   
    part2(p1.second)
}