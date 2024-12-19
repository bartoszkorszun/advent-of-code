import java.io.File

fun main() {
    val input = File("input.txt").readText().trim()
    input.toCharArray()

    val locations = mutableSetOf<Pair<Int, Int>>()
    var position = Pair(0, 0)
    locations.add(position)
    for (c in input) {
        position = move(c, position)
        locations.add(position)
    }

    println(locations.size)
}

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
