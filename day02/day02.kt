import java.io.File

fun main() {
    val fileName = "input.txt"
    val listOfArrays = mutableListOf<IntArray>()

    readFile(fileName, listOfArrays)

    var countSafe = 0

    for (array in listOfArrays) {
        var isDecreasing = array[0] > array[1]
        var isSafe = true

        for (j in 1 until array.size) {
            if ((isDecreasing && array[j - 1] < array[j]) || (!isDecreasing && array[j - 1] > array[j])) {
                isSafe = false
                break
            }
            if (Math.abs(array[j] - array[j - 1]) < 1 || Math.abs(array[j] - array[j - 1]) > 3) {
                isSafe = false
                break
            }
        }

        if (isSafe) {
            countSafe++
        }
    }

    println(countSafe)
}

fun readFile(fileName: String, listOfArrays: MutableList<IntArray>) {
    File(fileName).forEachLine { line ->
        val numbers = line.trim().split("\\s+".toRegex()).map { it.toInt() }.toIntArray()
        listOfArrays.add(numbers)
    }
}

