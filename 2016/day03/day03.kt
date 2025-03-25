import java.io.File

data class Triangle(val a: Int, val b: Int, val c: Int) {
    fun isValid(): Boolean {
        return a + b > c && a + c > b && b + c > a
    }
}

fun parseInput(input: String): List<Triangle> {
    return input.lines().map {
        val sides = it.trim().split("\\s+".toRegex()).map { it.toInt() }
        Triangle(sides[0], sides[1], sides[2])
    }
}

fun part2(triangles: List<Triangle>): Int {
    return 0
}   

fun part1(triangles: List<Triangle>): Int {
    return triangles.count { it.isValid() }
}

fun main() {
    val input = File("input.txt").readText().trim()
    val triangles = parseInput(input)
    
    val p1 = part1(triangles)
    val p2 = part2(triangles)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}