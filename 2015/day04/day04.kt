import java.io.File
import java.security.MessageDigest

fun findLowestNumber(secretKey: String, prefix: String): Int {
    val md5 = MessageDigest.getInstance("MD5")
    var number = 0

    while (true) {
        val input = secretKey + number
        val hash = md5.digest(input.toByteArray()).joinToString("") { "%02x".format(it) }

        if (hash.startsWith(prefix)) {
            return number
        }

        number++
    }

    return number
}

fun part1(input: String): Int {
    val secretKey = input
    val prefix = "00000"
    return findLowestNumber(secretKey, prefix)
}

fun part2(input: String): Int {
    val secretKey = input
    val prefix = "000000"
    return findLowestNumber(secretKey, prefix)
}

fun main() {
    val input = File("input.txt").readText().trim()

    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")
    println("Part 2: $p2")
}