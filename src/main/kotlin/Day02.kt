

fun main(args: Array<String>) {
    val input = readStringList("day02.txt")
    println(calc02_02(input))
}

fun calc02_02(input: List<String>): Int {
    var x = 0
    var y = 0
    var aim = 0
    input.forEach { cmd ->
        run {
            val split = cmd.split(" ")
            val value = split[1].toInt()
            when (split[0]) {
                "up" -> aim -= value
                "down" -> aim += value
                "forward" -> { x += value; y += value * aim }
            }
        }
    }
    return x*y
}


fun calc02_01(input: List<String>): Int {
    var x = 0
    var y = 0
    input.forEach { cmd ->
        run {
            val split = cmd.split(" ")
            val value = split[1].toInt()
            when (split[0]) {
                "up" -> y -= value
                "down" -> y -= value
                "forward" -> x += value
            }
        }
    }
    return x*y
}
