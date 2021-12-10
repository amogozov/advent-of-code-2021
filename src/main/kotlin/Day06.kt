fun main(args: Array<String>) {
    val input = readStringList("day06.txt")
    println(calc06(input, 80))
    println(calc06(input, 256))
}

fun calc06(input: List<String>, days: Int): Long {
    var state = LongArray(9)
    input[0].split(",").map { it.toInt() }.forEach { fish -> state[fish]++}

    for (i in 0 until days) {
        val next = LongArray(9)
        state.forEachIndexed { index, fish -> run {
            if (index == 0) {
                next[8] += fish
                next[6] += fish
            } else {
                next[index - 1] += fish
            }
        }}
        state = next
    }

    return state.sum()
}