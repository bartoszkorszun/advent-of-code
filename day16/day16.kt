import java.util.PriorityQueue
import java.io.File

//TODO

// Directions and their corresponding (dx, dy)
enum class Direction(val dx: Int, val dy: Int) {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1);

    fun rotateClockwise(): Direction = values()[(ordinal + 1) % values().size]
    fun rotateCounterClockwise(): Direction = values()[(ordinal + 3) % values().size]
}

// Data class for a state in the maze
data class State(val x: Int, val y: Int, val direction: Direction, val cost: Int) : Comparable<State> {
    override fun compareTo(other: State): Int = cost - other.cost
}

fun findMinimumScore(maze: List<String>): Int {
    val numRows = maze.size
    val numCols = maze[0].length

    // Find start (S) and end (E) positions
    var startX = 0
    var startY = 0
    var endX = 0
    var endY = 0
    for (i in maze.indices) {
        for (j in maze[i].indices) {
            when (maze[i][j]) {
                'S' -> {
                    startX = i
                    startY = j
                }
                'E' -> {
                    endX = i
                    endY = j
                }
            }
        }
    }

    // Priority queue for BFS with cost as the priority
    val queue = PriorityQueue<State>()
    queue.add(State(startX, startY, Direction.EAST, 0))

    // Visited set: (x, y, direction)
    val visited = mutableSetOf<Triple<Int, Int, Direction>>()

    // BFS with priority queue
    while (queue.isNotEmpty()) {
        val current = queue.poll()

        // If we reached the end, return the cost
        if (current.x == endX && current.y == endY) return current.cost

        // If the state is already visited, skip it
        if (Triple(current.x, current.y, current.direction) in visited) continue
        visited.add(Triple(current.x, current.y, current.direction))

        // Add neighbors (forward movement)
        val newX = current.x + current.direction.dx
        val newY = current.y + current.direction.dy
        if (newX in 0 until numRows && newY in 0 until numCols && maze[newX][newY] != '#') {
            queue.add(State(newX, newY, current.direction, current.cost + 1))
        }

        // Add neighbors (rotation)
        val clockwise = current.direction.rotateClockwise()
        val counterClockwise = current.direction.rotateCounterClockwise()
        queue.add(State(current.x, current.y, clockwise, current.cost + 1000))
        queue.add(State(current.x, current.y, counterClockwise, current.cost + 1000))
    }

    // If no path is found, return -1
    return -1
}

fun main() {
    val maze = File("input.txt").readLines()

    val result = findMinimumScore(maze)
    println("Minimum score: $result")
}
