import java.io.File

data class Instruction(
    val operation: String,
    val register: String? = null,
    val offset: Int? = null
)

fun parseInstruction(input: String): List<Instruction> {
    return input.lines().map { line ->
        when {
            line.startsWith("jie") || line.startsWith("jio") -> {
                val parts = line.split(" ", ", ")
                Instruction(parts[0], parts[1], parts[2].toInt())
            }

            line.startsWith("jmp") -> {
                val parts = line.split(" ")
                Instruction(parts[0], null, parts[1].toInt())
            }

            else -> {
                val parts = line.split(" ")
                Instruction(parts[0], parts[1], null)
            }
        }
    }
}

fun executeProgram(instructions: List<Instruction>, initialA: Int = 0, initialB: Int = 0): Pair<Int, Int> {
    val registers = mutableMapOf("a" to initialA, "b" to initialB)
    var programCounter = 0

    while (programCounter >= 0 && programCounter < instructions.size) {
        val instruction = instructions[programCounter]
        when (instruction.operation) {
            "hlf" -> {
                registers[instruction.register!!] = registers[instruction.register]!! / 2
                programCounter++
            }

            "tpl" -> {
                registers[instruction.register!!] = registers[instruction.register]!! * 3
                programCounter++
            }

            "inc" -> {
                registers[instruction.register!!] = registers[instruction.register]!! + 1
                programCounter++
            }

            "jmp" -> {
                programCounter += instruction.offset!!
            }

            "jie" -> {
                if (registers[instruction.register!!]!! % 2 == 0) {
                    programCounter += instruction.offset!!
                } else {
                    programCounter++
                }
            }

            "jio" -> {
                if (registers[instruction.register!!]!! == 1) {
                    programCounter += instruction.offset!!
                } else {
                    programCounter++
                }
            }
        }
    }
    return Pair(registers["a"]!!, registers["b"]!!)
}

fun part2(instructions: List<Instruction>): Int {
    val (_, registerB) = executeProgram(instructions, initialA = 1)
    return registerB
}   

fun part1(instructions: List<Instruction>): Int {
    val (_, registerB) = executeProgram(instructions)
    return registerB
}

fun main() {
    val input = File("input.txt").readText().trim()
    val instructions = parseInstruction(input)
    
    val p1 = part1(instructions)
    val p2 = part2(instructions)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}