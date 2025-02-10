import java.io.File

data class Location(
    val location1: String,
    val location2: String,
    val distance: Int
)

fun parseInput(input: String): List<Location> {
    val regex = Regex("""(\w+) to (\w+) = (\d+)""")
    return input.lines().mapNotNull { line ->
        regex.matchEntire(line)?.destructured?.let { (location1, location2, distance) ->
            Location(location1, location2, distance.toInt())
        }
    }
}

fun findLongestPath(
    currentLocation: String,
    locations: List<Location>,
    visited: MutableSet<String>,
    memo: MutableMap<Pair<String, Set<String>>, Int>
): Int {
    if (visited.size == locations.flatMap { listOf(it.location1, it.location2) }.toSet().size) {
        return 0
    }

    val key = Pair(currentLocation, visited.toSet())
    if (memo.containsKey(key)) {
        return memo[key]!!
    }

    var longestPath = 0
    locations.forEach { location ->
        val nextLocation = when (currentLocation) {
            location.location1 -> location.location2
            location.location2 -> location.location1
            else -> null
        }

        if (nextLocation != null && !visited.contains(nextLocation)) {
            visited.add(nextLocation)
            val distance = location.distance + findLongestPath(nextLocation, locations, visited, memo)
            longestPath = Math.max(longestPath, distance)
            visited.remove(nextLocation)
        }
    }

    memo[key] = longestPath
    return longestPath
}

fun findShortestPath(
    currentLocation: String,
    locations: List<Location>,
    visited: MutableSet<String>,
    memo: MutableMap<Pair<String, Set<String>>, Int>
): Int {
    if (visited.size == locations.flatMap { listOf(it.location1, it.location2) }.toSet().size) {
        return 0
    }

    val key = Pair(currentLocation, visited.toSet())
    if (memo.containsKey(key)) {
        return memo[key]!!
    }

    var shortestPath = Int.MAX_VALUE
    locations.forEach { location ->
        val nextLocation = when (currentLocation) {
            location.location1 -> location.location2
            location.location2 -> location.location1
            else -> null
        }

        if (nextLocation != null && !visited.contains(nextLocation)) {
            visited.add(nextLocation)
            val distance = location.distance + findShortestPath(nextLocation, locations, visited, memo)
            shortestPath = Math.min(shortestPath, distance)
            visited.remove(nextLocation)
        }
    }

    memo[key] = shortestPath
    return shortestPath
}

fun part2(input: List<Location>): Int {
    val locations = input.flatMap { listOf(it.location1, it.location2) }.toSet()
    var longestPath = 0
    val memo = mutableMapOf<Pair<String, Set<String>>, Int>()

    locations.forEach { location ->
        val visited = mutableSetOf(location)
        longestPath = Math.max(longestPath, findLongestPath(location, input, visited, memo))
    }

    return longestPath
}

fun part1(input: List<Location>): Int {
    val locations = input.flatMap { listOf(it.location1, it.location2) }.toSet()
    var shortestPath = Int.MAX_VALUE
    val memo = mutableMapOf<Pair<String, Set<String>>, Int>()

    locations.forEach { location ->
        val visited = mutableSetOf(location)
        shortestPath = Math.min(shortestPath, findShortestPath(location, input, visited, memo))
    }

    return shortestPath
}

fun main() {
    val input = File("input.txt").readText().trim()
    val locations = parseInput(input)
    
    val p1 = part1(locations)
    val p2 = part2(locations)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}