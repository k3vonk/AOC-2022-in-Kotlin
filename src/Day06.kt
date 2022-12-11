
fun main() {

    fun hasMatch(input: CharSequence, indicator: Int): Boolean {
        return input.toSet().size == indicator
    }

    fun findStartOfMessageMarker(input: String, marker: Int): Int {
        var substring: CharSequence?
        var i = marker

        while(i < input.length) {
            substring = input.subSequence(i - marker, i)
            if(hasMatch(substring, marker)) { break }
            i++
        }
        return i
    }

    fun part1(input: String): Int {
        return findStartOfMessageMarker(input, 4)
    }

    fun part2(input: String): Int {
        return findStartOfMessageMarker(input, 14)
    }

    val input = readInput("Day06_test")[0]
    println(part1(input))
    println(part2(input))
}