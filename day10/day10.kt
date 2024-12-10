import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val arr = createArr(input)

    var sum = 0

    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] == 0) {
                val positions = mutableListOf<Pair<Int, Int>>()
                findPath(arr, i, j, positions)     
                sum += positions.distinct().count()
            }
        }
    }

    println("Number of paths: $sum")
}

fun createArr(input: List<String>): Array<IntArray> { 
    return Array(input.size) { i -> input[i].map { it.toString().toInt() }.toIntArray() }
}

fun findPath(arr: Array<IntArray>, i: Int, j: Int, positions: MutableList<Pair<Int, Int>>) {
    if (arr[i][j] == 9) {
        positions.add(Pair(i, j))
        return
    }

    val up = i - 1
    val down = i + 1
    val left = j - 1
    val right = j + 1

    if (up >= 0 && arr[up][j] - arr[i][j] == 1) {
        findPath(arr, up, j, positions)
    }
    if (down < arr.size && arr[down][j] - arr[i][j] == 1) {
        findPath(arr, down, j, positions)
    }
    if (left >= 0 && arr[i][left] - arr[i][j] == 1) {
        findPath(arr, i, left, positions)
    }
    if (right < arr[i].size && arr[i][right] - arr[i][j] == 1) {
        findPath(arr, i, right, positions)
    }
}