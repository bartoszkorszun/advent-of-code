import java.io.File

fun findCombinationsWithSum(weights: List<Int>, target: Int, size: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun backtrack(start: Int, sum: Int, currentCombination: MutableList<Int>) {
        if (currentCombination.size == size) {
            if (sum == target) {
                result.add(currentCombination.toList())
            }
            return
        }

        if (sum > target || start >= weights.size) {
            return
        }

        for (i in start until weights.size) {
            currentCombination.add(weights[i])
            backtrack(i + 1, sum + weights[i], currentCombination)
            currentCombination.removeAt(currentCombination.size - 1)
        }
    }

    backtrack(0, 0, mutableListOf())

    return result.sortedBy { combo -> combo.fold(1L) { acc, weight -> acc * weight } }
}

fun canSplitIntoTwoEqualGroups(weights: List<Int>, targetSum: Int): Boolean {
    val dp = BooleanArray(targetSum + 1)
    dp[0] = true

    for (weight in weights) {
        for (j in targetSum downTo weight) {
            if (dp[j - weight]) {
                dp[j] = true
            }
        }
    }

    return dp[targetSum]
}

fun canSplitIntoThreeEqualGroups(weights: List<Int>, targetSum: Int): Boolean {
    for (i in 0 until (1 shl weights.size)) {
        val group = mutableListOf<Int>()
        var sum = 0

        for (j in weights.indices) {
            if (i shr j and 1 == 1) {
                group.add(weights[j])
                sum += weights[j]
            }
        }

        if (sum == targetSum) {
            val remaining = weights.toMutableList()
            group.forEach { remaining.remove(it) }

            if (canSplitIntoTwoEqualGroups(remaining, targetSum)) {
                return true
            }
        }
    }

    return false
}

fun part2(weights: List<Int>): Long {
    val totalWeight = weights.sum()
    val targetWeight = totalWeight / 4
    
    if (totalWeight % 4 != 0) {
        throw IllegalArgumentException("Total weight cannot be evenly divided into 4 groups")
    }
    
    var minGroupSize = 1
    var minQuantumEntanglement = Long.MAX_VALUE
    var foundValid = false
    
    while (!foundValid && minGroupSize <= weights.size / 4) {
        val combinations = findCombinationsWithSum(weights, targetWeight, minGroupSize)
        
        if (combinations.isNotEmpty()) {
            for (group1 in combinations) {
                val remainingWeights = weights.toMutableList()
                group1.forEach { remainingWeights.remove(it) }
                
                if (canSplitIntoThreeEqualGroups(remainingWeights, targetWeight)) {
                    val qe = group1.fold(1L) { acc, weight -> acc * weight }
                    minQuantumEntanglement = minOf(minQuantumEntanglement, qe)
                    foundValid = true
                }
            }
        }
        
        if (foundValid) {
            break
        }
        
        minGroupSize++
    }
    
    return minQuantumEntanglement
}   

fun part1(weights: List<Int>): Long {
    val totalWeight = weights.sum()
    val targetWeight = totalWeight / 3

    if (totalWeight % 3 != 0) {
        throw IllegalArgumentException("Total weight cannot be divided into three equal groups")
    }

    var minGroupSize = 1
    var minQuantumEntanglement = Long.MAX_VALUE
    var foundValid = false

    while (!foundValid && minGroupSize <= weights.size / 3) {
        val combinations = findCombinationsWithSum(weights, targetWeight, minGroupSize)

        if (combinations.isNotEmpty()) {
            for (group1 in combinations) {
                val remaining = weights.toMutableList()
                group1.forEach { remaining.remove(it) }

                if (canSplitIntoTwoEqualGroups(remaining, targetWeight)) {
                    val qe = group1.fold(1L) { acc, weight -> acc * weight }
                    minQuantumEntanglement = minOf(minQuantumEntanglement, qe)
                    foundValid = true
                }
            }
        }

        if (foundValid) {
            break
        }

        minGroupSize++
    }

    return minQuantumEntanglement
}

fun main() {
    val input = File("input.txt").readText().trim()
    val weights = input.lines().filter { it.isNotEmpty() }.map { it.toInt() }
    
    val p1 = part1(weights)
    val p2 = part2(weights)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}