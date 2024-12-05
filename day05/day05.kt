import java.io.File

fun main() {
    val fileName = "input.txt"
    val fileContent = File(fileName).readText()
    
    val parts = fileContent.split("\n\n")
    val rulesPart = parts[0]
    val updatesPart = parts[1]

    val rules = rulesPart.lines().map { line ->
        val (first, second) = line.split("|").map { it.toInt() }
        first to second
    }

    val updates = updatesPart.lines().map { line ->
        line.split(",").map { it.toInt() }
    }

    var isInOrder = true
    var count = 0

    for (update in updates) {
        isInOrder = true
        for (i in update.indices) {
            if (!checkRules(rules, update, i)) {
                isInOrder = false
                break
            }
        }

        if (isInOrder) {
            count += update[(update.size - 1)/2]
        }
    }

    println(count)
}

fun checkRules(rules: List<Pair<Int, Int>>, update: List<Int>, index: Int): Boolean {
    for (rule in rules) {
        if (update[index] == rule.first) {
            for (i in index downTo 0) {
                if (update[i] == rule.second && i < index) {
                    return false
                }
            }
        }
    }
    return true
}