import java.io.File

data class Replacement(
    val from: String,
    val to: String
)

fun parseInput(input: String): Pair<List<Replacement>, String> {
    val lines = input.lines().filter { it.isNotEmpty() }
    
    val replacementLines = lines.takeWhile { it.contains("=>") }
    val molecule = lines.last()
    
    val replacementList = replacementLines.map { line ->
        val (from, to) = line.split(" => ")
        Replacement(from, to)
    }
    
    return Pair(replacementList, molecule)
}

fun part2(replacements: List<Replacement>, target: String): Int {
    val sortedReplacements = replacements.sortedByDescending { it.to.length }
    
    var molecule = target
    var steps = 0
    
    while (molecule != "e") {
        val initialMolecule = molecule
        
        for (replacement in sortedReplacements) {
            val index = molecule.indexOf(replacement.to)
            if (index >= 0) {
                molecule = molecule.substring(0, index) + 
                           replacement.from + 
                           molecule.substring(index + replacement.to.length)
                steps++
                break
            }
        }
        
        if (initialMolecule == molecule) {
            println("Got stuck at: $molecule")
            return -1
        }
    }
    
    return steps
}   

fun part1(replacements: List<Replacement>, molecule: String): Int {
    val molecules = mutableSetOf<String>()

    for (replacement in replacements) {
        val (from, to) = replacement
        var index = 0
        while (index < molecule.length) {
            val nextIndex = molecule.indexOf(from, index)
            if (nextIndex == -1) {
                break
            }
            val newMolecule = molecule.substring(0, nextIndex) + to + molecule.substring(nextIndex + from.length)
            molecules.add(newMolecule)
            index = nextIndex + 1
        }
    }

    return molecules.size
}

fun main() {
    val input = File("input.txt").readText().trim()
    val (replacements, molecule) = parseInput(input)

    val p1 = part1(replacements, molecule)
    val p2 = part2(replacements, molecule)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}