import java.io.File

data class Triangle(val a: Int, val b: Int, val c: Int) {
    fun isValid(): Boolean {
        return a + b > c && a + c > b && b + c > a
    }
}

fun parseInputPart1(input: String): List<Triangle> {
    return input.lines().map {
        val sides = it.trim().split("\\s+".toRegex()).map { it.toInt() }
        Triangle(sides[0], sides[1], sides[2])
    }
}

fun parseInputPart2(input: String): List<Triangle> {
    val lines = input.lines().map { it.trim().split("\\s+".toRegex()).map { it.toInt() } }
    val triangles = mutableListOf<Triangle>()
    for (i in 0 until lines.size step 3) {
        for (j in 0 until 3) {
            triangles.add(Triangle(lines[i][j], lines[i + 1][j], lines[i + 2][j]))
        }
    }
    return triangles
}

fun part2(input: String): Int {
    val triangles = parseInputPart2(input)
    return triangles.count { it.isValid() }
}   

fun part1(input: String): Int {
    val triangles = parseInputPart1(input)
    return triangles.count { it.isValid() }
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}