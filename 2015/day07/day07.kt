import java.io.File

data class Instruction(
    val operation: String, 
    val operands: List<String>, 
    val destination: String
)

fun parseInstructions(input: String): List<Instruction> {
    val regex = Regex("((?!NOT)\\w+|\\d+)? ?(AND|OR|LSHIFT|RSHIFT|NOT)? ?(\\w+|\\d+)? -> (\\w+)")
    return input.lines().mapNotNull { line ->
        regex.matchEntire(line)?.destructured?.let { (op1, operation, op2, destination) ->
            val operands = listOfNotNull(op1, op2).filter { it.isNotEmpty() }
            Instruction(if (operation.isEmpty()) "ASSIGN" else operation, operands, destination)
        }
    }
}

fun evaluateSignal(wire: String, instructions: List<Instruction>, signals: MutableMap<String, Int>): Int {
    if (wire.toIntOrNull() != null) {
        return wire.toInt()
    }
    if (signals.containsKey(wire)) {
        return signals[wire]!!
    }
    val instruction = instructions.find { it.destination == wire } ?: throw IllegalArgumentException("No instruction for wire $wire")
    val signal = when (instruction.operation) {
        "ASSIGN" -> evaluateSignal(instruction.operands[0], instructions, signals)
        "AND" -> evaluateSignal(instruction.operands[0], instructions, signals) and evaluateSignal(instruction.operands[1], instructions, signals)
        "OR" -> evaluateSignal(instruction.operands[0], instructions, signals) or evaluateSignal(instruction.operands[1], instructions, signals)
        "LSHIFT" -> evaluateSignal(instruction.operands[0], instructions, signals) shl evaluateSignal(instruction.operands[1], instructions, signals)
        "RSHIFT" -> evaluateSignal(instruction.operands[0], instructions, signals) shr evaluateSignal(instruction.operands[1], instructions, signals)
        "NOT" -> evaluateSignal(instruction.operands[0], instructions, signals).inv() and 0xFFFF
        else -> throw IllegalArgumentException("Unknown operation ${instruction.operation}")
    }
    signals[wire] = signal
    return signal
}

fun part2(input: String): Int {
    val instructions = parseInstructions(input)
    val signals = mutableMapOf<String, Int>()
    signals["b"] = part1(input) 
    return evaluateSignal("a", instructions, signals)
}

fun part1(input: String): Int {
    val instructions = parseInstructions(input)
    val signals = mutableMapOf<String, Int>()
    return evaluateSignal("a", instructions, signals)
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}