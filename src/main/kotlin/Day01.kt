fun increases(measurements: List<Int>): Int {
    return measurements.zipWithNext().count { p -> p.first < p.second }
}

fun triplets(list: List<Int>): List<Int> {
    if (list.size <= 3) {
        return listOf(list.sum())
    }
    return listOf(list[0] + list[1] + list[2]) + triplets(list.drop(1))
}

fun main(args: Array<String>) {
    val input = readIntList("day01.txt")
    val triplets = triplets(input)
    println(increases(triplets))
}