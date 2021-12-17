import kotlin.math.min

fun main(args: Array<String>) {
    val input = readStringList("day15.txt")
    println(calc15_1(input))
    println(calc15_2(input))
}

fun calc15_2(input: List<String>): Int {
    val b = ArrayList<IntArray>()
    val n = input.size * 5
    val m = input[0].length * 5
    for (i in 0 until n) {
        val row = IntArray(m)
        for (j in 0 until m) {
            val oi = i % input.size
            val oj = j % input[0].length
            val d = i / input.size + j / input[0].length
            val o = input[oi][oj] - '0'
            row[j] = o + d
            if (row[j] > 9) {
                row[j] -= 9
            }
        }
        b += row
    }
    return solve15(b, n, m)
}

fun calc15_1(input: List<String>): Int {
    val b = ArrayList<IntArray>()
    val n = input.size
    val m = input[0].length
    for (line in input) {
        val row = IntArray(m)
        line.forEachIndexed { index, c -> row[index] = c - '0' }
        b += row
    }
    return solve15(b, n, m)
}

fun solve15(b: ArrayList<IntArray>, n: Int, m: Int): Int {
    var w = ArrayList<IntArray>()
    for (i in 0 until n) {
        val row = IntArray(m)
        for (j in 0 until m) {
            row[j] = Int.MAX_VALUE
        }
        w += row
    }
    w[0][0] = 0
    val d = listOf(0 to -1, -1 to 0, 0 to 1, 1 to 0)
    for (it in 0..3 * (n + m)) {
        val next = ArrayList<IntArray>()
        for (i in 0 until n) {
            val row = IntArray(m)
            for (j in 0 until m) {
                row[j] = w[i][j]
            }
            next += row
        }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (w[i][j] == Int.MAX_VALUE) {
                    continue
                }
                for (dd in d) {
                    val ni = i + dd.first
                    val nj = j + dd.second
                    if (ni >= 0 && nj >= 0 && ni < n && nj < n) {
                        next[ni][nj] = min(next[ni][nj], w[i][j] + b[ni][nj])
                    }
                }
            }
        }
        w = next
    }
    return w[n - 1][m - 1]
}
