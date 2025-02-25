import java.io.File

data class Reindeer(
    val name: String,
    val speed: Int,
    val flyTime: Int,
    val restTime: Int
)

fun parseInput(input: String): List<Reindeer> {
    val regex = Regex("""(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""")
    return input.lines().mapNotNull { line ->
        regex.matchEntire(line)?.destructured?.let { (name, speed, flyTime, restTime) ->
            Reindeer(name, speed.toInt(), flyTime.toInt(), restTime.toInt())
        }
    }
}

fun part2(reindeers: List<Reindeer>, time: Int): Int {
    val scores = mutableMapOf<String, Int>().withDefault { 0 }
    for (i in 1..time) {
        val distances = reindeers.map { reindeer ->
            val cycleTime = reindeer.flyTime + reindeer.restTime
            val cycles = i / cycleTime
            val remainingTime = i % cycleTime
            val flyTime = if (remainingTime > reindeer.flyTime) reindeer.flyTime else remainingTime
            cycles * reindeer.speed * reindeer.flyTime + flyTime * reindeer.speed
        }
        val maxDistance = distances.maxOrNull() ?: 0
        reindeers.forEachIndexed { index, reindeer ->
            if (distances[index] == maxDistance) {
                scores[reindeer.name] = scores.getValue(reindeer.name) + 1
            }
        }
    }
    return scores.values.maxOrNull() ?: 0
}   

fun part1(reindeers: List<Reindeer>, time: Int): Int {
    return reindeers.map { reindeer ->
        val cycleTime = reindeer.flyTime + reindeer.restTime
        val cycles = time / cycleTime
        val remainingTime = time % cycleTime
        val flyTime = if (remainingTime > reindeer.flyTime) reindeer.flyTime else remainingTime
        cycles * reindeer.speed * reindeer.flyTime + flyTime * reindeer.speed
    }.maxOrNull() ?: 0
}

fun main() {
    val input = File("input.txt").readText().trim()
    val reindeers = parseInput(input)
    val time = 2503
    
    val p1 = part1(reindeers, time)
    val p2 = part2(reindeers, time)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}