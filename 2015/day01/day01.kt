import java.io.File

fun main() {
    val input = File("input.txt").readText()
    input.toCharArray()

    var floor = 0
    var position = 1
    for (c in input) {
        if (c.code == 40) {
            floor++
        } 
        if (c.code == 41) {
            floor--
        }
        if (floor == -1) {
            println(position)
            break
        }
        position++
    }

    println(floor)
}
