import java.io.File

fun main() {
    
    val stones = File("input.txt").readText().split(" ")
        .map { it.toLong() }
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value.toLong() }
        .toMutableMap()

    repeat(75) { 
        val newStones = mutableMapOf<Long, Long>()

        for ((stone, count) in stones) {
            when {
                stone == 0L -> {
                    newStones[1L] = newStones.getOrDefault(1L, 0L) + count
                }
                isNumOfDigitsEven(stone) -> {
                    val (new1, new2) = divNum(stone)
                    newStones[new1] = newStones.getOrDefault(new1, 0L) + count
                    newStones[new2] = newStones.getOrDefault(new2, 0L) + count
                }
                else -> {
                    val newStone = stone * 2024L
                    newStones[newStone] = newStones.getOrDefault(newStone, 0L) + count
                }
            }
        }

        stones.clear()
        stones.putAll(newStones)
    }

    println(stones.values.sum())
}

fun isNumOfDigitsEven(num: Long): Boolean {
    var count = 0
    var temp = num
    while (temp > 0) {
        count++
        temp /= 10
    }
    return count % 2 == 0
}

fun divNum(num: Long): Pair<Long, Long> {
    val divisor = Math.pow(10.0, (num.toString().length / 2).toDouble()).toLong()
    val new1 = num / divisor
    val new2 = num % divisor
    return Pair(new1, new2)
}
