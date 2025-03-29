import java.io.File
import java.security.MessageDigest

fun part2(input: String): String {
    val password = CharArray(8) { '_' }
    var index = 0
    var charsFound = 0
    
    val md5 = MessageDigest.getInstance("MD5")
    
    while (charsFound < 8) {
        val hash = md5.digest("$input$index".toByteArray())
        val hashHex = hash.joinToString("") { "%02x".format(it) }
        
        if (hashHex.startsWith("00000")) {
            val position = hashHex[5].toString().toIntOrNull()
            if (position != null && position in 0..7 && password[position] == '_') {
                password[position] = hashHex[6]
                charsFound++
            }
        }
        
        index++
    }
    
    return password.joinToString("")
}   

fun part1(input: String): String {
    val password = StringBuilder()
    var index = 0

    val md5 = MessageDigest.getInstance("MD5")

    while (password.length < 8) {
        val hash = md5.digest("$input$index".toByteArray()).joinToString("") { "%02x".format(it) }
        if (hash.startsWith("00000")) {
            password.append(hash[5])
        }
        index++
    }

    return password.toString()
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}