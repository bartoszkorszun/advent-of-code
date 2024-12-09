import java.io.File

fun main() {
    val input = File("input.txt").readText().trim().map { it.toString().toInt() }.toIntArray()
    
    val fileBlocks = fillFileBlocks(input)

    changeArr(fileBlocks)

    println(calculateScore(fileBlocks))
}

fun fillFileBlocks(input: IntArray): MutableList<IntArray> {
    val fileBlocks = mutableListOf<IntArray>()

    for (i in 0 until input.size - 1 step 2) {
        val arr = intArrayOf(i / 2, input[i], input[i + 1])
        fileBlocks.add(arr)
    }

    val arr = intArrayOf((input.size - 1) / 2, input[input.size - 1], 0)
    fileBlocks.add(arr)

    return fileBlocks
}

fun changeArr(fileBlocks: MutableList<IntArray>) {
    var i = fileBlocks.size - 1
    var changed = false
    var noPairs = false
    while (i > 0) {
        for (j in 0 until i + 1) {
            if (fileBlocks[i][1] <= fileBlocks[j][2] && fileBlocks[i] != fileBlocks[j] && fileBlocks[i][0] != 0) {
                fileBlocks[i - 1][2] += fileBlocks[i][1] + fileBlocks[i][2]
                fileBlocks[i][2] = fileBlocks[j][2] - fileBlocks[i][1]
                fileBlocks[j][2] = 0
                val elem = fileBlocks.removeAt(i)
                fileBlocks.add(j + 1, elem)
                changed = true
                break
            }
            if (j == i) {
                noPairs = true
            }
        }
        if (!changed || noPairs) {
            i--
            changed = false
            noPairs = false
        }
    }
}

fun calculateScore(fileBlocks: MutableList<IntArray>): Long {
    var score = 0L
    var multiplier = 0L
    for (i in fileBlocks.indices) {
        for (j in 0 until fileBlocks[i][1]) {
            score += fileBlocks[i][0] * multiplier
            multiplier++
        }
        for (j in 0 until fileBlocks[i][2]) {
            multiplier++
        }
    }
    return score
}