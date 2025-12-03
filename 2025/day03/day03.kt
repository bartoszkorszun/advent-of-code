import java.io.File

fun findNumbers(line: String, index: Int, numsLeft: Int): Pair<Char, Int> {
    var num = '0'
    var newIndex = 0
    for (i in index..line.length-numsLeft) {
        if (line[i] > num) {
            num = line[i]
            newIndex = i
        }
        if (num == '9') break
    }
    return Pair(num, newIndex)
}

fun part2(input: List<String>): Long {
    var sum = 0L
    var currentIndex = 0
    var numsLeft = 12
    var sb: StringBuilder = StringBuilder()
    for (line in input) {
        while (numsLeft > 0) {
            val (num, newIndex) = findNumbers(line, currentIndex, numsLeft)
            sb.append(num)
            currentIndex = newIndex + 1
            numsLeft--
        }
        sum += sb.toString().toLong()

        currentIndex = 0
        numsLeft = 12
        sb.clear()
    }
    return sum
}   

fun part1(input: List<String>): Long {
    var sum = 0L
    var currentIndex = 0
    var numsLeft = 2
    var sb: StringBuilder = StringBuilder()
    for (line in input) {
        while (numsLeft > 0) {
            val (num, newIndex) = findNumbers(line, currentIndex, numsLeft)
            sb.append(num)
            currentIndex = newIndex + 1
            numsLeft--
        }
        sum += sb.toString().toLong()

        currentIndex = 0
        numsLeft = 2
        sb.clear()
    }
    return sum
}

fun main() {
    val input = File("input.txt").readText().trim().lines()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}