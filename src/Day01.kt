fun main() {
    fun part1(input: List<String>): Int {
        var highestCalories = 0
        var currentCalories = 0
        for (row in input) {
            if (row.isEmpty()) {
                if (currentCalories > highestCalories)
                    highestCalories = currentCalories

                currentCalories = 0
                continue
            }
            currentCalories += row.toInt()
        }
        return highestCalories
    }

    fun part2(input: List<String>): Int {
        val elfBaggage = mutableListOf<Int>()
        var currentCalories = 0

        for (row in input) {
            if (row.isEmpty()) {
                elfBaggage.add(currentCalories)
                currentCalories = 0
                continue
            }
            currentCalories += row.toInt()
        }

        elfBaggage.sort()
        return elfBaggage.takeLast(3).sum()
    }

    val input = readInput("Day01_test")

    println(part1(input))
    println(part2(input))
}
