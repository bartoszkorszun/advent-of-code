import java.io.File

fun main() {
    val fileName = "input.txt"
    val listOfArrays = mutableListOf<IntArray>()

    readFile(fileName, listOfArrays)

    var countSafe = 0

    for (array in listOfArrays) {
        if (checkArray(array)) {
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

fun checkArray(array: IntArray): Boolean {
    if (isStrictlyIncreasing(array) || isStrictlyDecreasing(array)) {
        return true
    }

    for (i in array.indices) {
        val newArray = array.filterIndexed { index, _ -> index != i }.toIntArray()
        if (isStrictlyIncreasing(newArray) || isStrictlyDecreasing(newArray)) {
            return true
        }
    }

    return false
}

fun isStrictlyIncreasing(array: IntArray): Boolean {
    for (i in 1 until array.size) {
        if (array[i] <= array[i - 1] || Math.abs(array[i] - array[i - 1]) > 3) {
            return false
        }
    }
    return true
}

fun isStrictlyDecreasing(array: IntArray): Boolean {
    for (i in 1 until array.size) {
        if (array[i] >= array[i - 1] || Math.abs(array[i] - array[i - 1]) > 3) {
            return false
        }
    }
    return true
}