import kotlin.math.abs
import kotlin.math.min

fun main(args: Array<String>) {
    val input = readStringList("day07.txt")
    println(calc07(input) { x -> x })
    println(calc07(input) { x -> x * (x + 1) / 2 })
}

fun calc07(input: List<String>, f: (Long) -> Long): Long {
    val x = input[0].split(",").map { it.toLong() }
    val min = x.minOf { it }
    val max = x.maxOf { it }

    var best = Long.MAX_VALUE
    for (y in min..max) {
        val cur = x.sumOf { f(abs(it - y)) }
        best = min(best, cur)
    }
    return best
}