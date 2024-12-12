import kotlin.io.path.Path
import kotlin.io.path.readText

//ToDo

fun main() {
    data class SimplePoint(val x: Int, val y: Int) {
        operator fun plus(other: SimplePoint) = SimplePoint(x + other.x, y + other.y)
        fun inBounds(width: Int, height: Int) = x in 0 until width && y in 0 until height
    }

    val directions = listOf(
        SimplePoint(1, 0), SimplePoint(-1, 0), // Right, Left
        SimplePoint(0, 1), SimplePoint(0, -1) // Down, Up
    )
    val horizontal = listOf(SimplePoint(1, 0), SimplePoint(-1, 0))
    val vertical = listOf(SimplePoint(0, 1), SimplePoint(0, -1))

    fun parseInput(input: List<String>) = input.map { it.toCharArray() }.toTypedArray()

    fun Array<CharArray>.isSameChar(c: Char, point: SimplePoint): Boolean =
        point.inBounds(this[0].size, this.size) && this[point.y][point.x] == c

    fun Array<CharArray>.flood(start: SimplePoint, seen: MutableSet<SimplePoint>): Set<SimplePoint> {
        val queue = ArrayDeque<SimplePoint>()
        val region = mutableSetOf(start)
        val charType = this[start.y][start.x]

        queue.add(start)
        seen.add(start)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (dir in directions) {
                val neighbor = current + dir
                if (!seen.contains(neighbor) && this.isSameChar(charType, neighbor)) {
                    seen.add(neighbor)
                    region.add(neighbor)
                    queue.add(neighbor)
                }
            }
        }

        return region
    }

    fun Array<CharArray>.perimeter(region: Set<SimplePoint>): Int =
        region.sumOf { point ->
            directions.count { dir -> !isSameChar(this[point.y][point.x], point + dir) }
        }

    fun Array<CharArray>.corners(region: Set<SimplePoint>): Int {
        var result = 0
        for (point in region) {
            for (hor in horizontal) {
                for (vert in vertical) {
                    val diagonal = point + hor + vert
                    val horizontalNeighbor = point + hor
                    val verticalNeighbor = point + vert
                    val charType = this[point.y][point.x]

                    if (!isSameChar(charType, horizontalNeighbor) && !isSameChar(charType, verticalNeighbor) ||
                        (!isSameChar(charType, diagonal) &&
                                isSameChar(charType, horizontalNeighbor) &&
                                isSameChar(charType, verticalNeighbor))
                    ) {
                        result++
                    }
                }
            }
        }
        return result
    }

    fun Array<CharArray>.makeRegions(): List<Set<SimplePoint>> {
        val seen = mutableSetOf<SimplePoint>()
        val regions = mutableListOf<Set<SimplePoint>>()

        for (y in this.indices) {
            for (x in this[y].indices) {
                val point = SimplePoint(x, y)
                if (!seen.contains(point)) {
                    regions.add(flood(point, seen))
                }
            }
        }
        return regions
    }

    fun part1(input: List<String>): Long {
        val data = parseInput(input)
        return data.makeRegions().sumOf { region ->
            val area = region.size
            val perimeter = data.perimeter(region)
            area.toLong() * perimeter
        }
    }

    fun part2(input: List<String>): Long {
        val data = parseInput(input)
        return data.makeRegions().sumOf { region ->
            val area = region.size
            val corners = data.corners(region)
            area.toLong() * corners
        }
    }

    val input = readInput("Day12") // Uses your input loading function
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun readInput(name: String) = Path("input.txt").readText().trim().lines()
