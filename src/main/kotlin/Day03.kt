fun main(args: Array<String>) {
    val input = readStringList("day03.txt")
    println(calc03_2(input))
}


fun calc03_1(input: List<String>): Int {
    val n = input[0].length
    val zeroes = IntArray(n)
    val ones = IntArray(n)
    input.forEach {
        it.forEachIndexed { index, c -> if (c == '0') zeroes[index]++ else ones[index]++ }
    }
    var gamma = ""
    var epsilon = ""
    for (i in 0 until n) {
        if (zeroes[i] > ones[i]) {
            gamma += "0"
            epsilon += "1"
        } else {
            gamma += "1"
            epsilon += "0"
        }
    }
    return gamma.toInt(2) * epsilon.toInt(2)
}

fun calc03_2(input: List<String>): Int {
    fun rating(l: List<String>, c: Char, p: Int): String {
        if (l.size == 1) {
            return l[0]
        }
        val zeroes = l.count { it[p] == '0' }
        val ones = l.count { it[p] == '1' }
        if (zeroes == ones) {
            return rating(l.filter { it[p] == c }, c, p + 1)
        }
        if (zeroes > ones) {
            if (c == '1') {
                return rating(l.filter { it[p] == '0' }, c, p + 1)
            } else {
                return rating(l.filter { it[p] == '1' }, c, p + 1)
            }
        } else {
            if (c == '1') {
                return rating(l.filter { it[p] == '1' }, c, p + 1)
            } else {
                return rating(l.filter { it[p] == '0' }, c, p + 1)
            }
        }
    }

    val o = rating(input, '1', 0)
    val c = rating(input, '0', 0)

    return o.toInt(2) * c.toInt(2)
}