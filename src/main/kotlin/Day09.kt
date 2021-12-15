fun main(args: Array<String>) {
    val input = readStringList("day09.txt")
    println(calc09_2(input))
}

val dir = listOf(-1 to 0, 0 to -1, 1 to 0, 0 to 1)

fun calc09_2(input: List<String>): Int {
    val b = ArrayList<ArrayList<Int>>()
    for (line in input) {
        val row = ArrayList<Int>()
        for (c in line)
            row += c - '0'
        b += row
    }
    val n = b.size
    val m = b[0].size

    val basins = ArrayList<Int>()
    for (i in 0 until n)
        for (j in 0 until m) {
            var lowest = true
            for (d in dir) {
                val ni = i + d.first
                val nj = j + d.second
                if (ni in 0 until n && nj in 0 until m) {
                    if (b[i][j] >= b[ni][nj]) {
                        lowest = false
                    }
                }
            }
            if (lowest) {
                basins += basin(b, n, m, i, j)
            }
        }
    val largest = basins.sorted().reversed()
    return largest[0] * largest[1] * largest[2]
}

fun basin(b: ArrayList<ArrayList<Int>>, n: Int, m: Int, i: Int, j: Int): Int {
    var size = 0
    val q = ArrayDeque<Pair<Int, Int>>()
    val w = HashSet<Pair<Int, Int>>()
    q.addLast(i to j)
    size++
    w.add(i to j)
    while (q.isNotEmpty()) {
        val c = q.removeFirst()
        for (d in dir) {
            val ni = c.first + d.first
            val nj = c.second + d.second
            if (ni in 0 until n && nj in 0 until m) {
                if (b[ni][nj] < 9 && !w.contains(ni to nj)) {
                    q.addLast(ni to nj)
                    size++
                    w.add(ni to nj)
                }
            }
        }
    }
    return size
}

fun calc09_1(input: List<String>): Int {
    val b = ArrayList<ArrayList<Int>>()
    for (line in input) {
        val row = ArrayList<Int>()
        for (c in line)
            row += c - '0'
        b += row
    }
    val n = b.size
    val m = b[0].size
    val dir = listOf(-1 to 0, 0 to -1, 1 to 0, 0 to 1)
    var risk = 0
    for (i in 0 until n)
        for (j in 0 until m) {
            var lowest = true
            for (d in dir) {
                val ni = i + d.first
                val nj = j + d.second
                if (ni in 0 until n && nj in 0 until m) {
                    if (b[i][j] >= b[ni][nj]) {
                        lowest = false
                    }
                }
            }
            if (lowest) {
                println("$i $j")
                println(b[i][j])
                risk += b[i][j] + 1
            }
        }
    return risk
}
