import java.io.File
import kotlin.math.max
import kotlin.math.min


fun part2(ranges: List<Pair<Long, Long>>): Long {
    val sortedRanges: List<LongRange> = ranges
        .map { (a, b) -> min(a, b)..max(a, b) }
        .sortedBy { it.first }

    val merged = mutableListOf<LongRange>()
    for (r in sortedRanges) {
        if (merged.isEmpty() || r.first > merged.last().last + 1) {
            merged.add(r)
        } else {
            val last = merged.last()
            merged[merged.lastIndex] = last.first..max(last.last, r.last)
        }
    }

    return merged.sumOf { it.last - it.first + 1 }
}   

fun part1(ranges: List<Pair<Long, Long>>, ids: List<Long>): Int {
    var sum = 0
    for (id in ids) {
        for (range in ranges) {
            if (id >= range.first && id <= range.second) {
                sum++
                break
            }
        }
    }
    return sum
}

fun main() {
    
    val lines = File("input.txt").readLines()
    val blankIndex = lines.indexOfFirst { it.isBlank() }

    val ranges = lines.take(blankIndex).map { line ->
        val (start, end) = line.split("-").map { it.trim().toLong() }
        start to end
    }

    val ids = lines.drop(blankIndex + 1).flatMap { line ->
        Regex("\\d+").findAll(line).map { it.value.toLong() }
    }

    val p1 = part1(ranges, ids)
    val p2 = part2(ranges)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}