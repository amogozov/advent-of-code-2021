import java.io.File

fun readStringList(fileName: String): List<String> {
    return File(ClassLoader.getSystemResource(fileName).file).readLines()
}

fun readIntList(fileName: String): List<Int> {
    return readStringList(fileName).map { it.toInt() }
}