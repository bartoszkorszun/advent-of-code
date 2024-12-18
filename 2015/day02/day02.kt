import java.io.File
import java.lang.Math

data class Box(
    val x: Int,
    val y: Int,
    val z: Int
)

fun part1(boxes: List<Box>): Int {
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

    return sumOfPaper
}

fun part2(boxes: List<Box>): Int {
    var sumOfRibbon = 0
    for (box in boxes) {
        val side1 = box.x + box.y
        val side2 = box.x + box.z
        val side3 = box.y + box.z

        val perimeter = 2 * Math.min(
            side1,
            Math.min(
                side2,
                side3
            )
        )

        val bow = box.x * box.y * box.z

        sumOfRibbon += perimeter + bow
    }

    return sumOfRibbon
}

fun parseInput(input: String): List<Box> {
    val lines = input.trim().lines().toString()
    val regex = Regex("""(\d+)x(\d+)x(\d+)""")

    return regex.findAll(lines).map {
        val (x, y, z) = it.destructured
        Box(x.toInt(), y.toInt(), z.toInt())
    }.toList()
}

fun main() {
    val input = File("input.txt").readText()
    val boxes = parseInput(input)

    val p1 = part1(boxes)
    val p2 = part2(boxes)

    println("Part 1: $p1")
    println("Part 2: $p2")
}

