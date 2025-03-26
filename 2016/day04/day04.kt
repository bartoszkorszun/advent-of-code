import java.io.File

data class Room(
    val name: String,
    val sector: Int,
    val checksum: String
)

fun parseInput(input: String): List<Room> {
    return input.lines().map {
        val parts = it.split("-")
        val name = parts.dropLast(1).joinToString("")
        val sector = parts.last().split("[").first().toInt()
        val checksum = parts.last().split("[").last().dropLast(1)
        Room(name, sector, checksum)
    }
}

fun part2(input: String): Int {
    val rooms = parseInput(input)
    val validRooms = rooms.filter { room ->
        val charCount = room.name.groupingBy { it }.eachCount()
        val sorted = charCount.entries.sortedWith(compareBy({ -it.value }, { it.key }))
        val checksum = sorted.take(5).joinToString("") { it.key.toString() }
        checksum == room.checksum
    }
    
    val correctRooms = input.lines().map {
        val parts = it.split("-")
        val name = parts.dropLast(1).joinToString("-")
        val sector = parts.last().split("[").first().toInt()
        val checksum = parts.last().split("[").last().dropLast(1)
        Room(name, sector, checksum)
    }
    
    fun decrypt(name: String, shift: Int): String {
        return name.map { char ->
            if (char == '-') ' '
            else {
                val shifted = ((char.code - 'a'.code + shift) % 26) + 'a'.code
                shifted.toChar()
            }
        }.joinToString("")
    }
    
    for (room in correctRooms) {
        if (validRooms.any { it.sector == room.sector }) { 
            val decrypted = decrypt(room.name, room.sector)
            if (decrypted.contains("northpole object storage")) {
                return room.sector
            }
        }
    }
    
    return -1
}  

fun part1(input: String): Int {
    val rooms = parseInput(input)
    val validRooms = rooms.filter { room ->
        val charCount = room.name.groupingBy { it }.eachCount()
        val sorted = charCount.entries.sortedWith(compareBy({ -it.value }, { it.key }))
        val checksum = sorted.take(5).joinToString("") { it.key.toString() }
        checksum == room.checksum
    }
    return validRooms.sumOf { it.sector }
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}