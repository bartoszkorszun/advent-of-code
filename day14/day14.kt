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
    val (maxX, maxY) = getMax()
    val p2robots = robots.map { it.copy() }

    repeat(100) {
        for (robot in robots) {
            moveRobot(robot, maxX, maxY)
        }
    }

    val result = countRobots(robots, maxX, maxY)
    println(result)

    val maxSeconds = maxX * maxY
    
    var bestTime = 0
    var bestSafetyFactor = Int.MAX_VALUE

    for (i in 0 until maxSeconds) {
        for (robot in p2robots) {
            moveRobot(robot, maxX, maxY)
        }

        val safetyFactor = calculateSafetyFactor(p2robots, maxX, maxY)

        if (safetyFactor < bestSafetyFactor) {
            bestSafetyFactor = safetyFactor
            bestTime = i + 1
            // printGrid(p2robots, maxX, maxY)
        }
    }

    println(bestTime)
}

fun getMax(): Pair<Int, Int> {
    return Pair(101,103)
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

fun countRobots(robots: List<Robot>, maxX: Int, maxY: Int): Int {

    val quadrants = Array(4) { 0 }

    for (robot in robots) {
        when {
            robot.pX < maxX / 2 && robot.pY < maxY / 2 -> quadrants[0]++
            robot.pX > maxX / 2 && robot.pY < maxY / 2 -> quadrants[1]++
            robot.pX < maxX / 2 && robot.pY > maxY / 2 -> quadrants[2]++
            robot.pX > maxX / 2 && robot.pY > maxY / 2 -> quadrants[3]++
        }
    }

    return quadrants.filter { it > 0 }.reduce(Int::times)
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

fun printGrid(robots: List<Robot>, maxX: Int, maxY: Int) {
    val grid = Array(maxY) { CharArray(maxX) { '.' } }

    for (robot in robots) {
        grid[robot.pY][robot.pX] = '#'
    }

    grid.forEach { println(it.joinToString("")) }
}