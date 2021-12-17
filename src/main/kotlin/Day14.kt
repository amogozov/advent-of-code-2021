import kotlin.collections.HashMap
import kotlin.math.min
import kotlin.math.max

fun main(args: Array<String>) {
    val input = readStringList("day14.txt")
    println(calc14(input, 10))
    println(calc14(input, 40))
}

fun calc14(input: List<String>, steps: Int): Long {
    val stateS = input[0]
    val opMap = HashMap<String, String>()
    val ops = ArrayList<String>()
    for (op in input.drop(2)) {
        val split = op.split(" -> ")
        opMap[split[0]] = split[1]
        ops += split[0]
    }

    fun encode(state: String): LongArray {
        val res = LongArray(ops.size)
        for (i in 1 until state.length) {
            val pair = state[i - 1] + "" + state[i]
            res[ops.indexOf(pair)]++
        }
        return res
    }

    fun getStates(op: Int): Pair<Int, Int> {
        val s = ops[op]
        val mid = opMap[s]!!
        val left = s[0] + mid
        val right = mid + s[1]
        return ops.indexOf(left) to ops.indexOf(right)
    }

    fun score(state: LongArray): Long {
        var min = Long.MAX_VALUE
        var max = Long.MIN_VALUE
        val cnt = HashMap<Char, Long>()
        for (i in state.indices) {
            if (state[i] == 0L) {
                continue
            }
            val s = ops[i]
            val l = { _: Char, v: Long? -> if (v == null) state[i] else v + state[i] }
            cnt.compute(s[0], l)
            cnt.compute(s[1], l)
        }
        println(cnt)
        for (c in cnt) {
            min = min(min, c.value / 2)
            max = max(max, c.value / 2)
        }
        return max - min + 1
    }

    var state = encode(stateS)
    for (step in 0 until steps) {
        val nextState = LongArray(ops.size)
        for (i in 0 until ops.size) {
            val (left, right) = getStates(i)
            nextState[left] += state[i]
            nextState[right] += state[i]
        }
        state = nextState
    }
    return score(state)
}
