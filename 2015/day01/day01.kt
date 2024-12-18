import java.io.File

fun main() {
    val input = File("input.txt").readText()
    input.toCharArray()

    var floor = 0
    for (c in input) {
        if (c.code == 40) {
            floor++
        } 
        if (c.code == 41) {
            floor--
        }
    }

    println(floor)
}
