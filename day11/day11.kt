import java.io.File

fun main() {
    val stones = File("input.txt").readText().split(" ").map { it.toString().toLong() }.toMutableList()

    for (j in 0 until 25) {
        var i = 0
        while (i < stones.size) {
            if (stones[i] == 0L) {
                stones[i] = 1L
            } else if (isNumOfDigitsEven(stones[i])) {
                val (new1, new2) = divNum(stones[i])
                stones[i] = new1
                i++
                stones.add(i, new2)
            } else {
                stones[i] *= 2024L
            }
            i++
        }
    }
    println(stones.size)
}

fun isNumOfDigitsEven(num: Long): Boolean {
    val str = num.toString()
    return if (str.length % 2 == 0) {
        true
    } else {
        false
    }
}

fun divNum(num: Long): Pair<Long,Long> {
    val str = num.toString()
    var (new1,new2) = Pair(0L,0L)
    new1 = str.substring(0, str.length / 2).toLong()
    new2 = str.substring(str.length / 2, str.length).toLong()
    return Pair(new1,new2)
}