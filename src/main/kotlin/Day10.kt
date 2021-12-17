import java.lang.Exception

fun main(args: Array<String>) {
    val input = readStringList("day10.txt")
    println(calc10_2(input))
}

val cost = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val cost2 = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
val opener = setOf('(', '[', '<', '{')
val pair = mapOf('(' to ')', '[' to ']', '<' to '>', '{' to '}')

fun calc10_2(input: List<String>): Long {
    val costs = ArrayList<Long>()
    for (line in input) {
        val stack = ArrayDeque<Char>()
        var corrupted = false
        for (c in line) {
            if (opener.contains(c)) {
                stack.addLast(c)
            } else {
                val top = stack.removeLastOrNull() ?: throw Exception()
                if (c != pair[top]) {
                    corrupted = true
                    break
                }
            }
        }
        if (corrupted) {
            continue
        }
        var cur = 0L
        while (stack.isNotEmpty()) {
            val top = stack.removeLast()
            val c = pair[top]
            cur = cur * 5L + cost2[c]!!
        }
        costs += cur
    }
    println(costs)
    val n = costs.size
    return costs.sorted()[n / 2]
}

fun calc10_1(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        val stack = ArrayDeque<Char>()
        for (c in line) {
            if (opener.contains(c)) {
                stack.addLast(c)
            } else {
                val top = stack.removeLastOrNull() ?: throw Exception()
                if (c != pair[top]) {
                    sum += cost[c]!!
                    break
                }
            }
        }
    }
    return sum
}
