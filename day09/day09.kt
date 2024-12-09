import java.io.File

fun main() {
    val input = File("test.txt").readText().trim().map { it.toString().toInt() }.toIntArray()
    
    val fileBlocks = Array((input.size - 1) / 2) { IntArray(3) }
    fillFileBlocks(input, fileBlocks)

    for (i in fileBlocks.indices) {
        println(fileBlocks[i].joinToString(", "))
    }
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

// 799772458