// PART 1
//
// import java.io.File
//
// fun main() {
//     val fileName = "input.txt"
//     val listOfArrays = mutableListOf<CharArray>()
//     val word = "XMAS"
//     var sum = 0

//     File(fileName).forEachLine { line ->
//         val charArray = line.toCharArray()
//         listOfArrays.add(charArray)
//     }

//     for (i in 0 until listOfArrays.size) {
//         for (j in 0 until listOfArrays[i].size) {
//             sum += countWordFromPosition(listOfArrays, i, j, word)
//         }
//     }

//     println("The number of times the word $word appears in the grid is $sum")
// }

// fun countWordFromPosition(grid: MutableList<CharArray>, row: Int, col: Int, word: String): Int {
//     val rowSize = grid.size
//     val colSize = grid[0].size

//     if (grid[row][col] != word[0]) {
//         return 0
//     }

//     val wordLength = word.length
//     val x = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1) 
//     val y = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1) 

//     var count = 0

//     for (dir in 0 until 8) {
//         var k = 1
//         var currX = row + x[dir]
//         var currY = col + y[dir]

//         while (k < wordLength) {
//             if (currX < 0 || currX >= rowSize || currY < 0 || currY >= colSize || grid[currX][currY] != word[k]) {
//                 break
//             }

//             currX += x[dir]
//             currY += y[dir]
//             k++
//         }

//         if (k == wordLength) {
//             count++
//         }
//     }

//     return count
// }

// PART 2

import java.io.File

fun main() {
    val fileName = "input.txt"
    val listOfArrays = mutableListOf<CharArray>()
    var count = 0

    File(fileName).forEachLine { line ->
        listOfArrays.add(line.toCharArray())
    }

    for (i in 0 until listOfArrays.size - 2) {
        for (j in 0 until listOfArrays[i].size - 2) {
            if (isXMasPattern(listOfArrays, i, j)) {
                count++
            }
        }
    }

    println("The number of X-MAS patterns found is $count")
}

fun isXMasPattern(grid: MutableList<CharArray>, row: Int, col: Int): Boolean {

    val diagonal1 = "${grid[row][col]}${grid[row + 1][col + 1]}${grid[row + 2][col + 2]}"

    val diagonal2 = "${grid[row][col + 2]}${grid[row + 1][col + 1]}${grid[row + 2][col]}"

    val isDiagonal1Valid = diagonal1 == "MAS" || diagonal1 == "SAM"
    val isDiagonal2Valid = diagonal2 == "MAS" || diagonal2 == "SAM"

    return isDiagonal1Valid && isDiagonal2Valid
}
