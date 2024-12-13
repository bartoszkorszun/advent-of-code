import java.io.File

data class ClawMachine(
    val aX: Int, 
    val aY: Int, 
    val bX: Int, 
    val bY: Int, 
    val prizeX: Int, 
    val prizeY: Int
)

fun main() {
    val input = File("input.txt").readText().trimIndent()

    val machines = parseInput(input)

    val results = machines.map { machine ->
        calculateMinTokens(machine) 
    }

    val solvableMachines = results.filterNotNull()
    val totalCost = solvableMachines.sum()

    println("Total prizes won: ${solvableMachines.size}")
    println("Minimum tokens required: $totalCost")
}

fun parseInput(input: String): List<ClawMachine> {
    val regex = Regex(
        """Button A: X\+(\d+), Y\+(\d+)\s+Button B: X\+(\d+), Y\+(\d+)\s+Prize: X=(\d+), Y=(\d+)"""
    )
    return regex.findAll(input).map { match ->
        val (aX, aY, bX, bY, prizeX, prizeY) = match.destructured
        ClawMachine(aX.toInt(), aY.toInt(), bX.toInt(), bY.toInt(), prizeX.toInt(), prizeY.toInt())
    }.toList()
}

fun calculateMinTokens(machine: ClawMachine, maxPresses: Int = 100): Int? {
    val (aX, aY, bX, bY, prizeX, prizeY) = machine

    var minTokens: Int? = null
    for (a in 0..maxPresses) {
        for (b in 0..maxPresses) {
            val currentX = a * aX + b * bX
            val currentY = a * aY + b * bY

            if (currentX == prizeX && currentY == prizeY) {
                val tokens = a * 3 + b * 1
                minTokens = if (minTokens == null) tokens else minOf(minTokens, tokens)
            }
        }
    }
    return minTokens
}