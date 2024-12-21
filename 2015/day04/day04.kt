import java.security.MessageDigest

fun main() {
    val secretKey = "ckczppom"
    val result = findLowestNumberWithFiveZeroes(secretKey)
    println("Lowest number: ${result.first}")
    println("Hash: ${result.second}")
}

fun findLowestNumberWithFiveZeroes(secretKey: String): Pair<Int, String> {
    var number = 1
    val md5 = MessageDigest.getInstance("MD5")

    while (true) {
        val input = "$secretKey$number"
        val hash = md5.digest(input.toByteArray()).toHexString()

        if (hash.startsWith("000000")) {
            return number to hash
        }
        number++
    }
}

fun ByteArray.toHexString(): String {
    return joinToString("") { "%02x".format(it) }
}
