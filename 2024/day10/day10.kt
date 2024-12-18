import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val arr = createArr(input)

    var sum = 0

    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] == 0) {
                sum += findPath(arr, i, j)     
            }
        }
    }

    println("Number of paths: $sum")
}

fun createArr(input: List<String>): Array<IntArray> { 
    return Array(input.size) { i -> input[i].map { it.toString().toInt() }.toIntArray() }
}

fun findPath(arr: Array<IntArray>, i: Int, j: Int): Int {
    if (arr[i][j] == 9) {
        return 1
    }

    val up = i - 1
    val down = i + 1
    val left = j - 1
    val right = j + 1
    var count = 0

    if (up >= 0 && arr[up][j] - arr[i][j] == 1) {
        count += findPath(arr, up, j)
    }
    if (down < arr.size && arr[down][j] - arr[i][j] == 1) {
        count += findPath(arr, down, j)
    }
    if (left >= 0 && arr[i][left] - arr[i][j] == 1) {
        count += findPath(arr, i, left)
    }
    if (right < arr[i].size && arr[i][right] - arr[i][j] == 1) {
        count += findPath(arr, i, right)
    }

    return count
}