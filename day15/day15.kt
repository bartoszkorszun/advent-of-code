import java.io.File

fun main() {
    val fileName = "test.txt"
    val (map, instructions) = readMapAndInstructions(fileName)
    var newMap = changeMap(map)
    
    var i = 0
    for (instruction in instructions) {
        for (c in instruction) {
            val robot = locateRobot(map)
            when (c) {
                '^' -> moveRobot(map, robot, -1, 0)
                'v' -> moveRobot(map, robot, 1, 0)
                '<' -> moveRobot(map, robot, 0, -1)
                '>' -> moveRobot(map, robot, 0, 1)
            }
        }
    }

    println("sum of GPS coordinates part 1: ${sumOfCoordinates(map)}")
    
    println(newMap.joinToString("\n") { it.joinToString("") })
}

fun readMapAndInstructions(fileName: String): Pair<MutableList<MutableList<Char>>, List<CharArray>> {
    val lines = File(fileName).readLines()
    val separatorIndex = lines.indexOfFirst { it.isBlank() }

    val mapLines = lines.subList(0, separatorIndex)
    val instructionsLines = lines.subList(separatorIndex + 1, lines.size)

    val map = mapLines.map { it.toMutableList() }.toMutableList()
    val instructions = instructionsLines.map { it.toCharArray() }

    return Pair(map, instructions)
}

fun locateRobot(map: MutableList<MutableList<Char>>): Pair<Int, Int> {
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == '@') {
                return Pair(i, j)
            }
        }
    }

    return Pair(-1, -1)
}

fun moveRobot(map: MutableList<MutableList<Char>>, robot: Pair<Int, Int>, dx: Int, dy: Int) {
    val (x, y) = robot
    val (newX, newY) = Pair(x + dx, y + dy)

    if (map[newX][newY] == '#' || (map[newX][newY] == 'O' && !isFreeSpace(map, newX, newY, dx, dy))) {
        return
    }

    if (map[newX][newY] == 'O') {
        moveBox(map, newX, newY, dx, dy)
    }

    map[x][y] = '.'
    map[newX][newY] = '@'
}

fun isFreeSpace(map: MutableList<MutableList<Char>>, x: Int, y: Int, dx: Int, dy: Int): Boolean {
    var newX = x
    var newY = y
    while(newX > 0 && newY > 0 && newX < map.size && newY < map[0].size) {
        if (map[newX][newY] == '#') {
            return false
        }
        if (map[newX][newY] == '.') {
            return true
        }
        newX += dx
        newY += dy
    }
    return false
}

fun moveBox(map: MutableList<MutableList<Char>>, x: Int, y: Int, dx: Int, dy: Int) {
    var newX = x
    var newY = y

    while (map[newX][newY] != '.') {
        newX += dx
        newY += dy
    }

    map[x][y] = '.'
    map[newX][newY] = 'O'
}

fun sumOfCoordinates(map: MutableList<MutableList<Char>>): Int {
    var sum = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == 'O') {
                sum += i * 100 + j
            }
        }
    }

    return sum
}

fun changeMap(map: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    var newMap = mutableListOf<MutableList<Char>>()
    for (i in map.indices) {
        newMap.add(mutableListOf())
        for (j in map[i].indices) {
            when (map[i][j]) {
                '@' -> {
                    newMap[i].add('@')
                    newMap[i].add('.')
                }
                'O' -> {
                    newMap[i].add('[')
                    newMap[i].add(']')
                }
                '.' -> {
                    newMap[i].add('.')
                    newMap[i].add('.')
                }
                '#' -> {
                    newMap[i].add('#')
                    newMap[i].add('#')
                }
            }
        }
    }

    return newMap
}