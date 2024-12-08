import java.io.File

fun main() {
    val fileName = "input.txt"
    val lines = File(fileName).readLines().map { it.toCharArray() }.toMutableList()

    var sumOfOverlapping = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until lines.size) {
        for (j in 0 until lines[i].size) {
            if (lines[i][j] != '.' && lines[i][j] != '#') {
                val c = lines[i][j]
                var pairs = mutableListOf<Pair<Int, Int>>()
                
                findPairs(i, j, lines, c, pairs)
                addAntinodes(i, j, pairs, lines, sumOfOverlapping)
            }
        }
    }
    
    println(sumOfOverlapping.distinct().size + lines.sumOf { it.count { it == '#' } })
}

fun findPairs(i: Int, j: Int, lines: MutableList<CharArray>, c: Char, pairs: MutableList<Pair<Int, Int>>) {
    for (row in i until lines.size) {
        for (col in 0 until lines[row].size) {  
            if (Pair(row, col) == Pair(i, j)) {
                continue
            }
            if (lines[row][col] == c) {
                pairs.add(Pair(row, col))
            }
        }
    }
}

fun addAntinodes(i: Int, j: Int, pairs: MutableList<Pair<Int, Int>>, lines: MutableList<CharArray>, sumOfOverlapping: MutableList<Pair<Int, Int>>) {

    for (pair in pairs) {
        val row1 = i - (pair.first - i)
        val col1 = j - (pair.second - j)
        val row2 = pair.first - (i - pair.first)
        val col2 = pair.second - (j - pair.second)

        if (row1 >= 0 && row1 < lines.size && col1 >= 0 && col1 < lines[row1].size) {
            if (lines[row1][col1] == '.') {
                lines[row1][col1] = '#'
            } else {
                if (lines[row1][col1] != '#') {
                    sumOfOverlapping.add(Pair(row1, col1))
                }
            }
        }
        if (row2 >= 0 && row2 < lines.size && col2 >= 0 && col2 < lines[row2].size) {
            if (lines[row2][col2] == '.') {
                lines[row2][col2] = '#'
            } else {
                if (lines[row2][col2] != '#') {
                    sumOfOverlapping.add(Pair(row2, col2))
                }
            }
        }
    }
}