fun containsInvalidCharacters(input: String): Boolean {
    return input.contains("i") || input.contains("o") || input.contains("l")
}

fun containsIncreasingStraight(input: String): Boolean {
    for (i in 0 until input.length - 2) {
        if (input[i] + 1 == input[i + 1] && input[i] + 2 == input[i + 2]) {
            return true
        }
    }
    return false
}

fun containsTwoPairs(input: String): Boolean {
    var pairs = 0
    var i = 0
    while (i < input.length - 1) {
        if (input[i] == input[i + 1]) {
            pairs++
            i += 2
        } else {
            i++
        }
    }
    return pairs >= 2
}

fun incrementString(input: String): String {
    val chars = input.toCharArray()
    var i = chars.size - 1
    while (i >= 0) {
        if (chars[i] == 'z') {
            chars[i] = 'a'
            i--
        } else {
            chars[i]++
            break
        }
    }
    return String(chars)
}