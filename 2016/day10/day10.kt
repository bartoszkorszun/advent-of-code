import java.io.File

sealed class Target {
    data class Bot(val id: Int) : Target()
    data class Output(val id: Int) : Target()
}

data class BotInstruction(
    val botId: Int,
    val lowTarget: Target,
    val highTarget: Target
)

data class ValueInstruction(
    val botId: Int,
    val value: Int
)

fun parseInput(input: String): Pair<List<ValueInstruction>, Map<Int, BotInstruction>> {
    val valueInstructions = mutableListOf<ValueInstruction>()
    val botInstructions = mutableMapOf<Int, BotInstruction>()

    val valueRegex = Regex("""value (\d+) goes to bot (\d+)""")
    val botRegex = Regex("""bot (\d+) gives low to (bot|output) (\d+) and high to (bot|output) (\d+)""")

    input.lines().filter { 
        it.isNotBlank()
    }.forEach { line ->
        if (line.startsWith("value")) {
            val match = valueRegex.find(line)!!
            val value = match.groupValues[1].toInt()
            val botId = match.groupValues[2].toInt()
            valueInstructions.add(ValueInstruction(botId, value))
        } else if (line.startsWith("bot")) {
            val match = botRegex.find(line)!!
            val botId = match.groupValues[1].toInt()

            val lowType = match.groupValues[2]
            val lowId = match.groupValues[3].toInt()
            val lowTarget = if (lowType == "bot") Target.Bot(lowId) else Target.Output(lowId)

            val highType = match.groupValues[4]
            val highId = match.groupValues[5].toInt()
            val highTarget = if (highType == "bot") Target.Bot(highId) else Target.Output(highId)

            botInstructions[botId] = BotInstruction(botId, lowTarget, highTarget)
        }
    }
    return Pair(valueInstructions, botInstructions)
}

fun runSimulation(input: String): Pair<Int, Map<Int, List<Int>>> {
    val (valueInstructions, botInstructions) = parseInput(input)

    val bots = mutableMapOf<Int, MutableList<Int>>()
    val outputs = mutableMapOf<Int, MutableList<Int>>()
    var botComparing61and17 = -1

    for (instruction in valueInstructions) {
        bots.getOrPut(instruction.botId) { mutableListOf() }.add(instruction.value)
    }

    while (true) {
        var actionPerformed = false

        val botsToProcess = bots.filter { it.value.size == 2 }.map { it.key }
        if (botsToProcess.isEmpty()) break

        for (botId in botsToProcess) {
            val values = bots[botId] ?: continue
            val instruction = botInstructions[botId] ?: continue

            val (low, high) = values.sorted()

            if ((low == 17 && high == 61) || (low == 61 && high == 17)) {
                botComparing61and17 = botId
            }

            when (instruction.lowTarget) {
                is Target.Bot -> bots.getOrPut(instruction.lowTarget.id) { mutableListOf() }.add(low)
                is Target.Output -> outputs.getOrPut(instruction.lowTarget.id) { mutableListOf() }.add(low)
            }

            when (instruction.highTarget) {
                is Target.Bot -> bots.getOrPut(instruction.highTarget.id) { mutableListOf() }.add(high)
                is Target.Output -> outputs.getOrPut(instruction.highTarget.id) { mutableListOf() }.add(high)
            }

            values.clear()
            actionPerformed = true
        }
        if (!actionPerformed) break;
    }
    return Pair(botComparing61and17, outputs)
}

fun part2(input: String): Int {
    val (_, outputs) = runSimulation(input)
    return outputs[0]!!.first() * outputs[1]!!.first() * outputs[2]!!.first()
}   

fun part1(input: String): Int {
    val (botComparing61and17, _) = runSimulation(input)
    return botComparing61and17
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}