
fun main() {

    fun hasMatch(input: CharSequence): Boolean {
        return input.toSet().size == 4
    }
    fun part1(input: String): Int {
        var substring: CharSequence?
        var i = 4

        while(i < input.length) {
            substring = input.subSequence(i - 4, i)
            if(hasMatch(substring)) { break }
            i++
        }
        return i
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day06_test")[0]
    println(part1(input))

}