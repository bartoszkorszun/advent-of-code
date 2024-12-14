import java.io.File

data class Robot(
    var pX: Int,
    var pY: Int,
    val vX: Int,
    val vY: Int
)

fun main() {
    val input = File("input.txt").readText()
    val robots = parseInput(input)
    val maxSeconds = 10403
    val (maxX, maxY) = getMax()

    var bestTime = 0
    var bestSafetyFactor = Int.MAX_VALUE

    for (time in 0 until maxSeconds) {
        // Move robots
        for (robot in robots) {
            moveRobot(robot, maxX, maxY)
        }

        // Calculate safety factor
        val safetyFactor = calculateSafetyFactor(robots, maxX, maxY)

        // Check for lowest safety factor (potential clustering)
        if (safetyFactor < bestSafetyFactor) {
            bestSafetyFactor = safetyFactor
            bestTime = time + 1
        }
    }

    println("Lowest safety factor occurred at second: $bestTime with safety factor: $bestSafetyFactor")
}

fun parseInput(input: String): List<Robot> {
    val regex = Regex(
        """p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)"""
    )

    return regex.findAll(input).map {
        val (pX, pY, vX, vY) = it.destructured
        Robot(pX.toInt(), pY.toInt(), vX.toInt(), vY.toInt())
    }.toList()
}

fun moveRobot(robot: Robot, maxX: Int, maxY: Int) {
    robot.pX = (robot.pX + robot.vX).mod(maxX)
    robot.pY = (robot.pY + robot.vY).mod(maxY)
}

fun calculateSafetyFactor(robots: List<Robot>, maxX: Int, maxY: Int): Int {
    val quadrants = Array(4) { 0 }

    for (robot in robots) {
        when {
            robot.pX < maxX / 2 && robot.pY < maxY / 2 -> quadrants[0]++
            robot.pX >= maxX / 2 && robot.pY < maxY / 2 -> quadrants[1]++
            robot.pX < maxX / 2 && robot.pY >= maxY / 2 -> quadrants[2]++
            robot.pX >= maxX / 2 && robot.pY >= maxY / 2 -> quadrants[3]++
        }
    }

    return quadrants.filter { it > 0 }.reduce(Int::times)
}

fun getMax(): Pair<Int, Int> {
    return Pair(101, 103)
}
