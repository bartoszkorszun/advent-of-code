sealed class Instruction {
    data class Rect(val width: Int, val height: Int) : Instruction()
    data class RotateRow(val row: Int, val amount: Int) : Instruction()
    data class RotateColumn(val column: Int, val amount: Int) : Instruction()
}

fun parseInstruction(line: String): Instruction {
    return when {
        line.startsWith("rect") -> {
            val (width, height) = line.substringAfter("rect ").split("x").map { it.toInt() }
            Instruction.Rect(width, height)
        }
        line.startsWith("rotate row") -> {
            val (row, amount) = line.substringAfter("row y=").split(" by ").map { it.toInt() }
            Instruction.RotateRow(row, amount)
        }
        line.startsWith("rotate column") -> {
            val (column, amount) = line.substringAfter("column x=").split(" by ").map { it.toInt() }
            Instruction.RotateColumn(column, amount)
        }
        else -> throw IllegalArgumentException("Unknown instruction: $line")
    }
}

fun parseInput(input: String): List<Instruction> {
    return input.lines().map { parseInstruction(it) }
}