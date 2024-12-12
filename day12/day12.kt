import java.io.File

fun main() {
    val input = File("input.txt").readLines().map { it.toMutableList() }.toMutableList()
    val visited = mutableSetOf<Pair<Int,Int>>()

    var cost = 0
    for (i in input.indices) {
        for (j in input[i].indices) {
            if (visited.contains(Pair(i, j))) {
                continue
            }
            val (area, perimeter) = findAndCalculateField(input, i, j, input[i][j], visited)    
            cost += area * perimeter
            println("cost of ${input[i][j]}: $cost")
        }
    }

    println("cost: $cost")
}

fun findAndCalculateField(
    input: MutableList<MutableList<Char>>, 
    i: Int, 
    j: Int, 
    current: Char, 
    visited: MutableSet<Pair<Int,Int>>
): Pair<Int, Int> {
    if (i < 0 || i >= input.size || j < 0 || j >= input[i].size || visited.contains(Pair(i, j)) || input[i][j] != current) {
        return Pair(0, 0)
    }
    
    visited.add(Pair(i, j))

    var area = 1
    var perimeter = 0

    val directions = listOf(
        Pair(-1, 0),
        Pair(0, 1),
        Pair(1, 0),
        Pair(0, -1)
    )

    for ((di, dj) in directions) {
        val ni = i + di
        val nj = j + dj

        if (ni >= 0 && ni < input.size && nj >= 0 && nj < input[ni].size && input[ni][nj] == current) {
            val (subArea, subPerimeter) = findAndCalculateField(input, ni, nj, current, visited)
            area += subArea
            perimeter += subPerimeter
        } else {
            perimeter++
        }
    }

    return Pair(area, perimeter)
}
