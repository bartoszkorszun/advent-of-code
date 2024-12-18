import java.io.File
import java.util.Arrays
import java.lang.Math

fun main() {
    val fileName = "input.txt"
    val firstArray = mutableListOf<Int>()
    val secondArray = mutableListOf<Int>()

    readFile(fileName, firstArray, secondArray)

    val firstArrayResult = firstArray.toIntArray()
    val secondArrayResult = secondArray.toIntArray()

    Arrays.sort(firstArrayResult)
    Arrays.sort(secondArrayResult)

    val sum = calcSum(firstArrayResult, secondArrayResult)
    println("Sum: $sum")

    val similarity = calcSimilarity(firstArrayResult, secondArrayResult)
    println("Similarity: $similarity")
}

fun readFile(fileName: String, firstArray: MutableList<Int>, secondArray: MutableList<Int>) {
    File(fileName).forEachLine { line ->
        val parts = line.trim().split("\\s+".toRegex())
        if (parts.size == 2) {
            firstArray.add(parts[0].toInt())
            secondArray.add(parts[1].toInt())
        }
    }
}

fun calcSum(firstArray: IntArray, secondArray: IntArray): Int {
    val n = firstArray.size
    var sum = 0

    for (i in 0 until n) {
        sum += Math.abs(firstArray[i] - secondArray[i])
    }

    return sum
}

fun calcSimilarity(firstArray: IntArray, secondArray: IntArray): Int {
    val n = firstArray.size
    var similarity = 0

    var pointer = 0
    for (i in 0 until n) {
        val curr = firstArray[i]
        var counter = 0
        while (secondArray[pointer] <= curr) {
            if (secondArray[pointer] == curr) {
                counter++
            }
            pointer++
            if (pointer == n) {
                break
            }
        }
        similarity += counter * curr
    }

    return similarity
}