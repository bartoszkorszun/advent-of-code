import java.io.File

enum class Direction {
    NORTH, EAST, SOUTH, WEST
}

fun part2(directions: List<String>): Int {
    var x = 0
    var y = 0
    var dir = Direction.NORTH

    val visited = mutableSetOf<Pair<Int, Int>>()
    visited.add(Pair(x, y))
    
    directions.forEach {
        val turn = it[0]
        val steps = it.substring(1).toInt()
        
        if (turn == 'R') {
            dir = when (dir) {
                Direction.NORTH -> Direction.EAST
                Direction.EAST -> Direction.SOUTH
                Direction.SOUTH -> Direction.WEST
                Direction.WEST -> Direction.NORTH
            }
        } else {
            dir = when (dir) {
                Direction.NORTH -> Direction.WEST
                Direction.WEST -> Direction.SOUTH
                Direction.SOUTH -> Direction.EAST
                Direction.EAST -> Direction.NORTH
            }
        }
        
        for (i in 1..steps) {
            when (dir) {
                Direction.NORTH -> y++
                Direction.SOUTH -> y--
                Direction.EAST -> x++
                Direction.WEST -> x--
            }
            
            // Check if we've visited this location before
            val currentPos = Pair(x, y)
            if (visited.contains(currentPos)) {
                return Math.abs(x) + Math.abs(y)
            }
            
            visited.add(currentPos)
        }
    }
    return -1
}   

fun part1(directions: List<String>): Int {
    var x = 0
    var y = 0
    var dir = Direction.NORTH
    
    directions.forEach {
        val turn = it[0]
        val steps = it.substring(1).toInt()
        
        if (turn == 'R') {
            dir = when (dir) {
                Direction.NORTH -> Direction.EAST
                Direction.EAST -> Direction.SOUTH
                Direction.SOUTH -> Direction.WEST
                Direction.WEST -> Direction.NORTH
            }
        } else {
            dir = when (dir) {
                Direction.NORTH -> Direction.WEST
                Direction.WEST -> Direction.SOUTH
                Direction.SOUTH -> Direction.EAST
                Direction.EAST -> Direction.NORTH
            }
        }
        
        when (dir) {
            Direction.NORTH -> y += steps
            Direction.SOUTH -> y -= steps
            Direction.EAST -> x += steps
            Direction.WEST -> x -= steps
        }
    }
    return Math.abs(x) + Math.abs(y)
}

fun main() {
    val input = File("input.txt").readText().trim()
    val directions = input.split(", ")
    
    val p1 = part1(directions)
    val p2 = part2(directions)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}