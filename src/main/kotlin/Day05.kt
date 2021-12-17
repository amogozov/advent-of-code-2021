import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main(args: Array<String>) {
    val input = readStringList("day05.txt")
    println(calc05_2(input))
}

fun calc05_1(input: List<String>): Int {
    val n = 1000
    val board = ArrayList<IntArray>()
    for (i in 0 until n) {
        board.add(IntArray(n))
    }
    input.forEach { line ->
        run {
            val points = line.split(" -> ")
            val p1 = points[0].trim().split(",")
            val p2 = points[1].trim().split(",")
            val x1 = p1[0].toInt()
            val y1 = p1[1].toInt()
            val x2 = p2[0].toInt()
            val y2 = p2[1].toInt()
            if ((x1 == x2 || y1 == y2)) {
                mark(board, x1, y1, x2, y2)
            }
        }
    }
    return score05(board)
}

fun calc05_2(input: List<String>): Int {
    val n = 1000
    val board = ArrayList<IntArray>()
    for (i in 0 until n) {
        board.add(IntArray(n))
    }
    input.forEach { line ->
        run {
            val points = line.split(" -> ")
            val p1 = points[0].trim().split(",")
            val p2 = points[1].trim().split(",")
            val x1 = p1[0].toInt()
            val y1 = p1[1].toInt()
            val x2 = p2[0].toInt()
            val y2 = p2[1].toInt()
            if (x1 == x2 || y1 == y2 || abs(x1 - x2) == abs(y1 - y2)) {
                mark(board, x1, y1, x2, y2)
            }
        }
    }
    return score05(board)
}

fun score05(board: ArrayList<IntArray>): Int {
    return board.sumOf { row -> row.sumOf { if (it > 1) 1L else 0 } }.toInt()
}

fun mark(board: ArrayList<IntArray>, sx: Int, sy: Int, ex: Int, ey: Int) {
    val dx = (ex - sx).sign
    val dy = (ey - sy).sign
    val steps = max(abs(sx - ex), abs(sy - ey))
    for (s in 0..steps) {
        board[sy + dy * s][sx + dx * s]++
    }
}
