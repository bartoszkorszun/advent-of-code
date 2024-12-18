import java.io.File
import java.lang.Math

data class Box(
    val x: Int,
    val y: Int,
    val z: Int
)

fun main() {
    val input = File("input.txt").readText().trim()
    val boxes = parseInput(input)

    var sumOfPaper = 0
    for (box in boxes) {
        val side1 = box.x * box.y
        val side2 = box.x * box.z
        val side3 = box.y * box.z

        val extra = Math.min(
            side1,
            Math.min(
                side2,
                side3
            )
        )

        sumOfPaper += 2 * (side1 + side2 + side3) + extra
    }

    println(sumOfPaper)
}

fun parseInput(input: String): List<Box> {
    val lines = input.lines().toString()
    val regex = Regex("""(\d+)x(\d+)x(\d+)""")

    return regex.findAll(lines).map {
        val (x, y, z) = it.destructured
        Box(x.toInt(), y.toInt(), z.toInt())
    }.toList()
}
