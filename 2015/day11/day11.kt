import java.io.File

fun part2(input: String): String {
    return part1(input)
}   

fun part1(input: String): String {
    var password = input
    while (true) {
        password = incrementString(password)
        if (containsInvalidCharacters(password)) continue
        if (!containsIncreasingStraight(password)) continue
        if (!containsTwoPairs(password)) continue
        return password
    }
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(p1)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}