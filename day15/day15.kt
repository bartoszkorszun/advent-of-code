import java.io.File

fun main() {
    val fileName = "test.txt"
    val (map, instructions) = readMapAndInstructions(fileName)
    var newMap = changeMap(map)
    
    for (instruction in instructions) {
        for (c in instruction) {
            val robot = locateRobot(map)
            when (c) {
                '^' -> moveRobot1(map, robot, -1, 0)
                'v' -> moveRobot1(map, robot, 1, 0)
                '<' -> moveRobot1(map, robot, 0, -1)
                '>' -> moveRobot1(map, robot, 0, 1)
            }
        }
    }

    println("sum of GPS coordinates part 1: ${sumOfCoordinates(map)}")

    val boxCords = findBoxCords(newMap)
    var i = 0
    println(newMap.joinToString("\n") { it.joinToString("") })
    for (instruction in instructions) {
        for (c in instruction) {
            i++
            if (i == 6) {
                break
            }
            val robot = locateRobot(newMap)
            when (c) {
                '^' -> moveRobot2(newMap, robot, boxCords, -1, 0)
                'v' -> moveRobot2(newMap, robot, boxCords, 1, 0)
                '<' -> moveRobot2(newMap, robot, boxCords, 0, -1)
                '>' -> moveRobot2(newMap, robot, boxCords, 0, 1)
            }
        }
    }

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

fun moveRobot1(map: MutableList<MutableList<Char>>, robot: Pair<Int, Int>, dx: Int, dy: Int) {
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

fun moveRobot2(map: MutableList<MutableList<Char>>, robot: Pair<Int, Int>, boxCords: MutableList<Pair<Pair<Int, Int>, Int>>, dx: Int, dy: Int) {
    val (x, y) = robot
    val (newX, newY) = Pair(x + dx, y + dy)

    if (map[newX][newY] == '#') {
        return
    }

    if (map[newX][newY] != '.') {
        if (dy != 0) {
            if (!isSafeToMoveHorizontally(map, newX, newY, dx, dy)) {
                return
            }
        } 
        if (dx != 0) {
            if (!isSafeToMoveVertically(map, boxCords, newX, newY, dx, dy)) {
                return
            }
        }
    }

    map[x][y] = '.'
    map[newX][newY] = '@'
}

fun isSafeToMoveHorizontally(map: MutableList<MutableList<Char>>, x: Int, y: Int, dx: Int, dy: Int): Boolean {
    
    var newY = y

    while (newY > 0 && newY < map[x].size) {
        if (map[x][newY] == '#') {
            return false
        }
        if (map[x][newY] == '.') {
            break
        }
        newY += dy
    }
    // println("newY: $newY, y: $y, dy: $dy, ${map[x].size}")
    moveBigBoxH(map, x, y, newY)

    return true
}

fun isSafeToMoveVertically(map: MutableList<MutableList<Char>>, boxCords: MutableList<Pair<Pair<Int, Int>, Int>>, x: Int, y: Int, dx: Int, dy: Int): Boolean {
    

    return false
}

fun moveBigBoxH(map: MutableList<MutableList<Char>>, x: Int, y: Int, newY: Int) {
    if (y < newY) {
        for (i in y..newY) {
            val tmp = map[x][i]
            map[x][i] = map[x][i+1]
            map[x][i+1] = tmp
        }
    } else {
        for (i in newY..y) {
            val tmp = map[x][i]
            map[x][i] = map[x][i-1]
            map[x][i-1] = tmp
        }
    }
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

fun findBoxCords(map: MutableList<MutableList<Char>>): MutableList<Pair<Pair<Int, Int>, Int>> {
    val boxCords = mutableListOf<Pair<Pair<Int, Int>, Int>>()
    var i = 0
    var j = 0

    while (i < map.size) {
        j = 0
        while (j < map[i].size) {
            if (map[i][j] == '[') {
                boxCords.add(Pair(Pair(j, j+1), i))
                j++
            }
            j++
        }
        i++
    }

    return boxCords
}