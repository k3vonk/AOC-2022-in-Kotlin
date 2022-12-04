fun main() {

    fun List<String>.part1(): Int {
        var count = 0
        for (assignment in this) {
            val one = assignment
                .substringBefore(",")
                .split("-")
                .map { it.toInt() }
            val two = assignment
                .substringAfter(",")
                .split("-")
                .map { it.toInt() }

            val rangeOne = one.first() ..one.last()
            val rangeTwo = two.first() .. two.last()
            if (two.first() in rangeOne && two.last() in rangeOne) {
                count++
            } else if (one.first() in rangeTwo && one.last() in rangeTwo) {
                count++
            }
        }
        return count
    }
    val assignments = readInput("Day04_test")

    println(assignments.part1())
}