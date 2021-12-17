fun main(args: Array<String>) {
    val input = readStringList("day12.txt")
    println(calc12(input, true))
    println(calc12(input, false))
}

fun calc12(input: List<String>, twiced: Boolean): Int {
    val h = HashMap<String, Int>()
    val g = HashMap<String, HashSet<String>>()
    var res = 0

    fun add(v: String) {
        if (h.contains(v))
            return
        h[v] = h.size
    }

    fun addEdge(u: String, v: String) {
        if (!g.containsKey(u)) {
            g[u] = HashSet()
        }
        g[u]!!.add(v)
    }

    fun go(u: String, mask: IntArray, twiced: Boolean) {
        if (u == "end") {
            res++
            return
        }
        for (v in g[u]!!) {
            val msk = h[v]!!
            if (v[0] in 'a'..'z') {
                if (mask[msk] < 1) {
                    mask[msk]++
                    go(v, mask, twiced)
                    mask[msk]--
                } else if (mask[msk] == 1 && !twiced) {
                    mask[msk]++
                    go(v, mask, true)
                    mask[msk]--
                }
            } else {
                go(v, mask, twiced)
            }
        }
    }

    for (line in input) {
        val split = line.trim().split("-")
        val u = split[0]
        val v = split[1]
        add(u)
        add(v)
        addEdge(u, v)
        addEdge(v, u)
    }

    val mask = IntArray(h.size)
    mask[h["start"]!!] = 2
    go("start", mask, twiced)
    return res
}
