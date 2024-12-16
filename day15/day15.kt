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
    printMap(newMap)
    for (instruction in instructions) {
        for (c in instruction) {
            val boxCords = mutableListOf<Pair<Pair<Int, Int>, Int>>()
            val robot = locateRobot(newMap)
            when (c) {
                '^' -> moveRobot2(newMap, robot, boxCords, -1, 0)
                'v' -> moveRobot2(newMap, robot, boxCords, 1, 0)
                '<' -> moveRobot2(newMap, robot, boxCords, 0, -1)
                '>' -> moveRobot2(newMap, robot, boxCords, 0, 1)
            }
        }
    }

    printMap(newMap)
    println("sum of GPS coordinates part 2: ${sumOfCoordinates(newMap)}")
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
            if (isSafeToMoveVertically(map, boxCords, newX, newY, dx, dy)) {
                for (box in boxCords) {
                    val (y1, y2) = box.first
                    val x = box.second
                    
                    map[x + dx][y1] = '['
                    map[x + dx][y2] = ']'
                    map[x][y1] = '.'
                    map[x][y2] = '.'
                }
            } else {
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
    moveBigBoxH(map, x, y, newY)

    return true
}

fun isSafeToMoveVertically(map: MutableList<MutableList<Char>>, boxCords: MutableList<Pair<Pair<Int, Int>, Int>>, x: Int, y: Int, dx: Int, dy: Int): Boolean {

    when(map[x][y]) {
        '[' -> {
            if (map[x + dx][y] == '[' && isSafeToMoveVertically(map, boxCords, x + dx, y, dx, dy)) {
                boxCords.add(Pair(Pair(y, y + 1), x))
                return true
            }
            if (isSafeToMoveVertically(map, boxCords, x + dx, y, dx, dy) && isSafeToMoveVertically(map, boxCords, x + dx, y + 1, dx, dy)) {
                boxCords.add(Pair(Pair(y, y + 1), x))
                return true
            }
        }
        ']' -> {
            if (map[x + dx][y] == ']' && isSafeToMoveVertically(map, boxCords, x + dx, y, dx, dy)) {
                boxCords.add(Pair(Pair(y - 1, y), x))
                return true
            }
            if (isSafeToMoveVertically(map, boxCords, x + dx, y, dx, dy) && isSafeToMoveVertically(map, boxCords, x + dx, y - 1, dx, dy)) {
                boxCords.add(Pair(Pair(y - 1, y), x))
                return true
            }
        }
        '#' -> {
            return false
        }
        '.' -> {
            return true
        }
    }

    return false
}

fun moveBigBoxH(map: MutableList<MutableList<Char>>, x: Int, y: Int, newY: Int) {
    if (y < newY) {
        for (i in newY - 1 downTo y) {
            val tmp = map[x][i]
            map[x][i] = map[x][i+1]
            map[x][i+1] = tmp
        }
    } else {
        for (i in newY..y) {
            val tmp = map[x][i]
            map[x][i] = map[x][i+1]
            map[x][i+1] = tmp
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
            if (map[i][j] == 'O' || map[i][j] == '[') {
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

fun printMap(map: MutableList<MutableList<Char>>) {
    for (i in map.indices) {
        println(map[i].joinToString(""))
    }
}