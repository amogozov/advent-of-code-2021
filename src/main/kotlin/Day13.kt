fun main(args: Array<String>) {
    val input = readStringList("day13.txt")
    println(calc13(input, true))
    println(calc13(input, false))
}

fun transform(p: Pair<Int, Int>, f: List<String>): Pair<Int, Int> {
    val axis = f[1].toInt()
    if (f[0] == "x") {
        if (p.first < axis) {
            return p
        } else {
            return axis - (p.first - axis) to p.second
        }
    } else {
        if (p.second < axis) {
            return p
        } else {
            return p.first to axis - (p.second - axis)
        }
    }
}

fun calc13(input: List<String>, onlyFirst: Boolean): Int {
    var points = HashSet<Pair<Int, Int>>()
    for (line in input) {
        if (line.isEmpty()) {
            break
        }
        val point = line.split(",")
        points.add(point[0].toInt() to point[1].toInt())
    }
    val folds = input.filter { it.startsWith("fold") }
    for (fold in folds) {
        val firstFold = fold.split(" ")[2].split("=")
        val newPoints = HashSet<Pair<Int, Int>>()
        for (point in points) {
            val t = transform(point, firstFold)
            newPoints.add(t)
        }
        points = newPoints
        if (onlyFirst) {
            break
        }
    }

    if (onlyFirst) {
        return points.size
    }

    val b = ArrayList<CharArray>()
    for (r in 0 until 6) {
        val row = CharArray(39) { '.' }
        b += row
    }
    for (p in points) {
        b[p.second][p.first] = '#'
    }
    for (r in b) {
        println(r)
    }

    return points.size
}