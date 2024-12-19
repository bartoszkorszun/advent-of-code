import java.io.File

fun move(c: Char, position: Pair<Int, Int>): Pair<Int, Int> {
    var (x, y) = position

    when (c) {
        '^' -> x++
        'v' -> x--
        '>' -> y++
        '<' -> y--
    }

    return Pair(x, y)
}

fun part1(input: String): Int {
    val locations = mutableSetOf<Pair<Int, Int>>()
    var position = Pair(0, 0)
    locations.add(position)
    for (c in input) {
        position = move(c, position)
        locations.add(position)
    }

    return locations.size
}

fun part2(input: String): Int {
    val locations = mutableSetOf<Pair<Int, Int>>()
    var santasPosition = Pair(0, 0)
    var robotsPosition = Pair(0, 0)
    locations.add(santasPosition)
    for (i in input.indices) {
        if (i % 2 == 0) {
            santasPosition = move(input[i], santasPosition)
            locations.add(santasPosition)
        } else {
            robotsPosition = move(input[i], robotsPosition)
            locations.add(robotsPosition)
        }
    }

    return locations.size
}

fun main() {
    val input = File("input.txt").readText().trim()

    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")
    println("Part 2: $p2")
}
