import java.io.File

fun main() {
    val input = File("input.txt").readText().trim().map { it.toString().toInt() }.toIntArray()
    
    val fileBlocks = Array((input.size + 1) / 2) { IntArray(3) }
    fillFileBlocks(input, fileBlocks)

    var finalArr = createFinalArr(fileBlocks)

    println(calculateScore(finalArr))
}

fun fillFileBlocks(input: IntArray, fileBlocks: Array<IntArray>) {
    for (i in 0 until input.size - 1 step 2) {
        fileBlocks[i / 2][0] = i / 2
        fileBlocks[i / 2][1] = input[i]
        fileBlocks[i / 2][2] = input[i + 1]
    }
    fileBlocks[(input.size - 1) / 2][0] = (input.size - 1) / 2
    fileBlocks[(input.size - 1) / 2][1] = input[input.size - 1]
    fileBlocks[(input.size - 1) / 2][2] = 0
}

fun createFinalArr(fileBlocks: Array<IntArray>): MutableList<Int> {
    val finalArr = mutableListOf<Int>()
    var k = 0

    for (i in fileBlocks.indices) {
        while (fileBlocks[i][1] != 0) {
            finalArr.add(fileBlocks[i][0])
            fileBlocks[i][1]--
        }
        while (fileBlocks[i][2] != 0 && fileBlocks[fileBlocks.size - 1 - k][1] != 0) {
            finalArr.add(fileBlocks[fileBlocks.size - 1 - k][0])
            fileBlocks[i][2]--
            fileBlocks[fileBlocks.size - 1 - k][1]--

            if (fileBlocks[fileBlocks.size - 1 - k][1] == 0) {
                k++
            }
        }
    }

    return finalArr
}

fun calculateScore(finalArr: MutableList<Int>): Long {
    var score = 0L

    for (i in finalArr.indices) {
        score += finalArr[i] * i
    }

    return score
}

// 799772458