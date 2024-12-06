import java.io.File

fun main() {
    val fileName = "input.txt"
    val fileContent = File(fileName).readLines()

    val matrix = fileContent.map { line -> line.toCharArray().toMutableList() }.toMutableList()

    var guard: Pair<Int, Int>? = null

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if (matrix[i][j] == '^') {
                guard = i to j
                break
            }
        }
    }

    while (true) {
        val (i, j) = guard!!
        val direction = matrix[i][j]

        if (checkEdges(matrix, guard, direction)) {
            break
        }

        guard = moveAndMark(matrix, guard, direction)
    }

    val result = countX(matrix) + 1
    println(result)
}

fun moveAndMark(matrix: MutableList<MutableList<Char>>, guard: Pair<Int, Int>, direction: Char): Pair<Int, Int> {
    val (i, j) = guard
    var newI = i
    var newJ = j

    when (direction) {
        '^' -> {
            if (matrix[i - 1][j] == '#') {
                matrix[i][j] = '>'
                return i to j
            }
            newI--
        }
        'v' -> {
            if (matrix[i + 1][j] == '#') {
                matrix[i][j] = '<'
                return i to j
            }
            newI++
        }
        '<' -> {
            if (matrix[i][j - 1] == '#') {
                matrix[i][j] = '^'
                return i to j
            }
            newJ--    
        }
        '>' -> {
            if (matrix[i][j + 1] == '#') {
                matrix[i][j] = 'v'
                return i to j
            }
            newJ++
        }
    }

    matrix[i][j] = 'X'
    matrix[newI][newJ] = direction

    return newI to newJ
}

fun checkEdges(matrix: MutableList<MutableList<Char>>, guard: Pair<Int, Int>, direction: Char): Boolean {
    val (i, j) = guard

    return when (direction) {
        '^' -> i == 0
        'v' -> i == matrix.size - 1
        '<' -> j == 0
        '>' -> j == matrix[i].size - 1
        else -> false
    }
}

fun countX(matrix: MutableList<MutableList<Char>>): Int {
    var count = 0
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if (matrix[i][j] == 'X') {
                count++
            }
        }
    }
    return count
}