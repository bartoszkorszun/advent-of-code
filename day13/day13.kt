// TODO

import kotlin.io.path.Path
import kotlin.io.path.readText

data class ClawItem(val buttonA: LongPoint, val buttonB: LongPoint, val prize: LongPoint)

data class LongPoint(val x: Long, val y: Long)

fun main() {

    val zero = LongPoint(0L, 0L)

    fun parseInput(input: List<String>): List<ClawItem> {
        var i = 0
        val result = mutableListOf<ClawItem>()
        while (i < input.size) {
            val a = input[i++].substringAfter("Button A: ").split(", ")
            val buttonA = LongPoint(a[0].substringAfter("X").toLong(), a[1].substringAfter("Y").toLong())
            val b = input[i++].substringAfter("Button B: ").split(", ")
            val buttonB = LongPoint(b[0].substringAfter("X").toLong(), b[1].substringAfter("Y").toLong())
            val p = input[i++].substringAfter("Prize: ").split(", ")
            val prize = LongPoint(p[0].substringAfter("X=").toLong(), p[1].substringAfter("Y=").toLong())
            result.add(ClawItem(buttonA, buttonB, prize))
            i++
        }
        return result.toList()
    }

    fun determinant(a: LongPoint, b: LongPoint): Long {
        return a.x * b.y - a.y * b.x
    }

    fun LongPoint.mod(d: Long): LongPoint = LongPoint(this.x % d, this.y % d)
    fun LongPoint.div(d: Long): LongPoint = LongPoint(this.x / d, this.y / d)

    fun ClawItem.tokens(prizeDist: Long): Long {
        val px = prize.x + prizeDist
        val py = prize.y + prizeDist
        val mult = LongPoint(buttonB.y * px - buttonB.x * py, -buttonA.y * px + buttonA.x * py)
        val d = determinant(buttonA, buttonB)
        return if (mult.mod(d) == zero) {
            val ab = mult.div(d)
            3 * ab.x + 1 * ab.y
        } else {
            0L
        }
    }

    fun part1(input: List<String>): Long {
        return parseInput(input).asSequence().map { it.tokens(0L) }.sum()
    }

    fun part2(input: List<String>): Long {
        return parseInput(input).asSequence().map { it.tokens(10_000_000_000_000L) }.sum()
    }

    val input = readInput("input")
    println(part1(input))

    println(part2(input))
}

fun readInput(name: String) = Path("$name.txt").readText().trim().lines()