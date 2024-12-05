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

    var count = 0
    var repairedCount = 0

    for (update in updates) {
        var isInOrder = true
        for (i in update.indices) {
            if (!checkRules(rules, update, i)) {
                isInOrder = false
                val repairedUpdate = repairUpdate(rules, update)
                repairedCount += repairedUpdate[repairedUpdate.size / 2]
                println("update: $update, repairedUpdate: $repairedUpdate")
                break
            }
        }

        if (isInOrder) {
            count += update[(update.size) / 2]
        } 
    }

    println("count: $count, repairedCount: $repairedCount")
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

fun repairUpdate(rules: List<Pair<Int, Int>>, update: List<Int>): List<Int> {
    val repairedUpdate = update.toMutableList()
    while (true) {
        var isRepaired = true
        for (i in repairedUpdate.indices) {
            if (!checkRules(rules, repairedUpdate, i)) {
                isRepaired = false
                swap(rules, repairedUpdate, i)
                break
            }
        }
        if (isRepaired) {
            break
        }
    }
    return repairedUpdate
}

fun swap(rules: List<Pair<Int, Int>>, repairedUpdate: MutableList<Int>, index: Int) {
    for (rule in rules) {
        if (repairedUpdate[index] == rule.first) {
            for (i in index downTo 0) {
                if (repairedUpdate[i] == rule.second && i < index) {
                    repairedUpdate[index] = rule.second
                    repairedUpdate[i] = rule.first
                }
            }
        }
    }
}