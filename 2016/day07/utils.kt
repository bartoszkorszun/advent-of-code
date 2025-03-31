// PART 1

fun hasAbba(s: String): Boolean {
    for (i in 0 until s.length - 3) {
        if (s[i] != s[i + 1] && s[i] == s[i + 3] && s[i + 1] == s[i + 2]) {
            return true
        }
    }
    return false
}

fun supportsTls(ip: String): Boolean {
    val parts = ip.split("[", "]")
    
    val outsideBrackets = parts.filterIndexed { index, _ -> index % 2 == 0 }
    val insideBrackets = parts.filterIndexed { index, _ -> index % 2 == 1 }

    val hasAbbaOutside = outsideBrackets.any { hasAbba(it) }
    val hasAbbaInside = insideBrackets.any { hasAbba(it) }

    return hasAbbaOutside && !hasAbbaInside
}

// PART 2

fun findAbaSequences(s: String): List<String> {
    val sequences = mutableListOf<String>()
    for (i in 0 until s.length - 2) {
        if (s[i] != s[i + 1] && s[i] == s[i + 2]) {
            sequences.add(s.substring(i, i + 3))
        }
    }
    return sequences
}

fun abaToBab(s: String): String {
    return "${s[1]}${s[0]}${s[1]}"
}

fun supportsSSL(ip: String): Boolean {
    val parts = ip.split("[", "]")
    
    val outsideBrackets = parts.filterIndexed { index, _ -> index % 2 == 0 }
    val insideBrackets = parts.filterIndexed { index, _ -> index % 2 == 1 }

    val abaSequences = outsideBrackets.flatMap { findAbaSequences(it) }
    val babSequences = abaSequences.map { abaToBab(it) }

    return babSequences.any { bab -> 
        insideBrackets.any { it.contains(bab) } 
    }
}