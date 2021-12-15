import java.lang.Exception

fun main(args: Array<String>) {
    val input = readStringList("day08_input.txt")
    println(calc08_2(input))
}

fun calc08_1(input: List<String>): Long {
    var res = 0L
    for (line in input) {
        val split = line.split("|")
        val digits = split[1].trim().split(" ")
        res += digits.sumOf { digit -> if (digit.length in setOf(2, 3, 4, 7)) 1L else 0 }
    }
    return res
}

val sigs08 = mapOf("012456" to "0", "25" to "1", "02346" to "2", "02356" to "3", "1235" to "4", "01356" to "5",
    "013456" to "6", "025" to "7", "0123456" to "8", "012356" to "9")

fun calc08_2(input: List<String>): Long {
    val w = BooleanArray(7)
    val p = IntArray(7)
    var found = false
    var sol = IntArray(7)

    fun check(wires: List<String>): Boolean {
        for (digit in wires) {
            val sig = digit.map { p[it - 'a'] }.sorted().joinToString(separator = "")
            if (sig !in sigs08)
                return false
        }
        return true
    }

    fun rec(wires: List<String>, step: Int) {
        if (found)
            return
        if (step == 7) {
            if (check(wires)) {
                sol = p.clone()
                found = true
            }
            return
        }
        for (c in 0 until 7) {
            if (!w[c]) {
                w[c] = true
                p[step] = c
                rec(wires, step + 1)
                w[c] = false
            }
        }
    }
    var res = 0L
    for (line in input) {
        val split = line.split("|")
        val wires = split[0].trim().split(" ")
        found = false
        rec(wires, 0)
        if (!found) {
            throw Exception()
        }
        val digits = split[1].trim().split(" ")
        res += score08(digits, sol)
    }
    return res
}

fun score08(digits: List<String>, sol: IntArray): Long {
    return digits
        .map { digit -> sigs08[digit.map { sol[it - 'a'] }.sorted().joinToString(separator = "")]}
        .joinToString(separator = "")
        .toLong()
}
