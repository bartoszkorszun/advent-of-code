import java.io.File

fun part2(input: String): Int {
    val target = input.toInt()
    val houses = IntArray(target / 10)
    for (i in 1 until houses.size) {
        for (j in i until houses.size step i) {
            if (j <= i * 50) {
                houses[j] += i * 11
                if (houses[i] >= target) {
                    return i
                }
            }
        }
    }
    return -1
}   

fun part1(input: String): Int {
    val target = input.toInt()
    val houses = IntArray(target / 10)
    for (i in 1 until houses.size) {
        for (j in i until houses.size step i) {
            houses[j] += i * 10
            if (houses[i] >= target) {
                return i
            }
        }
    }
    return -1
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}