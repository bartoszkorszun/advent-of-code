import java.io.File
import java.util.Arrays
import java.lang.Math

fun main() {
    val fileName = "input.txt"
    val firstArray = mutableListOf<Int>()
    val secondArray = mutableListOf<Int>()

    File(fileName).forEachLine { line ->
        val parts = line.trim().split("\\s+".toRegex())
        if (parts.size == 2) {
            firstArray.add(parts[0].toInt())
            secondArray.add(parts[1].toInt())
        }
    }

    val firstArrayResult = firstArray.toIntArray()
    val secondArrayResult = secondArray.toIntArray()

    Arrays.sort(firstArrayResult)
    Arrays.sort(secondArrayResult)

    val n = firstArrayResult.size
    var sum = 0

    for (i in 0 until n) {
        sum += Math.abs(firstArrayResult[i] - secondArrayResult[i])
    }

    println(sum)
}