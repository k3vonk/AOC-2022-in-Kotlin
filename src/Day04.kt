fun main() {

    val assignments = readInput("Day04_test")

    var count = 0
    var part2 = 0
    for (assignment in assignments) {
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

        if (two.first() in rangeOne) {
            part2++
        } else if (two.last() in rangeOne) {
            part2++
        } else if (one.first() in rangeTwo) {
            part2++
        } else if (one.last() in rangeTwo) {
            part2++
        }
    }

    println(count)
    println(part2)
}