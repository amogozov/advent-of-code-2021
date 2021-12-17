fun main(args: Array<String>) {
    val input = readStringList("day04.txt")
    println(calc04_1(input))
    println(calc04_2(input))
}

fun calc04_1(input: List<String>): Int {
    val bingos = input[0].split(",").map { it.toInt() }
    val boardList = input.drop(1)
    val n = boardList.size / 6
    val (boards, boardsState) = parseBoards(boardList)
    bingos.forEach { bingo ->
        run {
            for (b in 0 until n) {
                val cb = boards[b]
                for (i in 0 until 5)
                    for (j in 0 until 5)
                        if (cb[i][j] == bingo) {
                            boardsState[b][i][j] = true
                        }
                if (bingoed(boardsState[b])) {
                    return score(cb, boardsState[b]) * bingo
                }
            }
        }
    }
    return 0
}

fun calc04_2(input: List<String>): Int {
    val bingos = input[0].split(",").map { it.toInt() }
    val boardList = input.drop(1)
    val n = boardList.size / 6
    val (boards, boardsState) = parseBoards(boardList)
    val won = BooleanArray(n)
    var wc = 0
    bingos.forEach { bingo ->
        run {
            for (b in 0 until n) {
                if (won[b])
                    continue
                val cb = boards[b]
                for (i in 0 until 5)
                    for (j in 0 until 5)
                        if (cb[i][j] == bingo) {
                            boardsState[b][i][j] = true
                        }
                if (bingoed(boardsState[b])) {
                    won[b] = true
                    wc++
                    if (wc == n) {
                        return score(cb, boardsState[b]) * bingo
                    }
                }
            }
        }
    }
    return 0
}

fun parseBoards(boardList: List<String>): Pair<ArrayList<List<List<Int>>>, ArrayList<List<BooleanArray>>> {
    val boards = ArrayList<List<List<Int>>>()
    val boardsState = ArrayList<List<BooleanArray>>()
    val n = boardList.size / 6
    var row = 0
    for (b in 0 until n) {
        val board = ArrayList<List<Int>>()
        val boardState = ArrayList<BooleanArray>()
        row += 1
        for (i in 0..4) {
            board += boardList[row].trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            row += 1
            val row = BooleanArray(5)
            for (j in 0..4) {
                row[j] = false
            }
            boardState += row
        }
        boards += board
        boardsState += boardState
    }
    return Pair(boards, boardsState)
}

fun score(cb: List<List<Int>>, list: List<BooleanArray>): Int {
    var s = 0
    cb.forEachIndexed { i, row -> row.forEachIndexed { j, num -> if (!list[i][j]) s += num } }
    return s
}

fun bingoed(cstate: List<BooleanArray>): Boolean {
    var res = false
    for (i in 0 until 5) {
        res = res or cstate[i].all { it }
    }
    for (i in 0 until 5) {
        var col = true
        for (j in 0 until 5) {
            col = col and cstate[j][i]
        }
        res = res or col
    }
    return res
}
