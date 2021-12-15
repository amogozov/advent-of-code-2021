fun main(args: Array<String>) {
    val input = readStringList("day11.txt")
    println(calc11_01(input, 100))
    println(calc11_02(input))
}

fun calc11_01(input: List<String>, steps: Int): Int {
    val b = ArrayList<IntArray>()
    val n = input.size
    val m = input[0].length
    for (line in input) {
        val row = IntArray(m)
        for (j in 0 until m) {
            row[j] = line[j] - '0'
        }
        b += row
    }
    //printBoard(b)
    var res = 0
    for (step in 0 until steps) {
        val f = ArrayList<BooleanArray>()
        for (i in 0 until n) {
            val row = BooleanArray(m)
            for (j in 0 until m) {
                row[j] = false
            }
            f += row
        }
        val q = ArrayDeque<Pair<Int, Int>>()
        for (i in 0 until n)
            for (j in 0 until m)
                if (b[i][j] < 9) {
                    b[i][j]++
                } else {
                    f[i][j] = true
                    q.addLast(i to j)
                    res++
                }
        //printBoard(b)
        while (q.isNotEmpty()) {
            val (i, j) = q.removeFirst()
            for (di in -1..1)
                for (dj in -1..1) {
                    if (di == 0 && dj == 0)
                        continue
                    if (!(i + di in 0 until n && j + dj in 0 until m)) {
                        continue
                    }
                    if (f[i + di][j + dj]) {
                        continue
                    }
                    b[i + di][j + dj]++
                    if (b[i + di][j + dj] > 9) {
                        f[i + di][j + dj] = true
                        q.addLast(i + di to j + dj)
                        res++
                    }
                }
        }
        for (i in 0 until n)
            for (j in 0 until m)
                if (f[i][j])
                    b[i][j] = 0
        //
    }
    printBoard(b)

    return res
}

fun calc11_02(input: List<String>): Int {
    val b = ArrayList<IntArray>()
    val n = input.size
    val m = input[0].length
    for (line in input) {
        val row = IntArray(m)
        for (j in 0 until m) {
            row[j] = line[j] - '0'
        }
        b += row
    }
    //printBoard(b)
    var step = 0
    while (true) {
        step++
        val f = ArrayList<BooleanArray>()
        var flashed = 0
        for (i in 0 until n) {
            val row = BooleanArray(m)
            for (j in 0 until m) {
                row[j] = false
            }
            f += row
        }
        val q = ArrayDeque<Pair<Int, Int>>()
        for (i in 0 until n)
            for (j in 0 until m)
                if (b[i][j] < 9) {
                    b[i][j]++
                } else {
                    f[i][j] = true
                    q.addLast(i to j)
                    flashed++
                }
        //printBoard(b)
        while (q.isNotEmpty()) {
            val (i, j) = q.removeFirst()
            for (di in -1..1)
                for (dj in -1..1) {
                    if (di == 0 && dj == 0)
                        continue
                    if (!(i + di in 0 until n && j + dj in 0 until m)) {
                        continue
                    }
                    if (f[i + di][j + dj]) {
                        continue
                    }
                    b[i + di][j + dj]++
                    if (b[i + di][j + dj] > 9) {
                        f[i + di][j + dj] = true
                        q.addLast(i + di to j + dj)
                        flashed++
                    }
                }
        }
        for (i in 0 until n)
            for (j in 0 until m)
                if (f[i][j])
                    b[i][j] = 0
        if (flashed == n * m) {
            break
        }
        //
    }
    printBoard(b)

    return step
}


fun printBoard(b: ArrayList<IntArray>) {
    for (row in b) {
        for (c in row) {
            print(c)
        }
        println()
    }
    println("-----------")
}
