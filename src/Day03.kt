fun main() {

    fun convertToPriority(item: Char): Int {
        return if (item.isUpperCase()) {
            item.code - 'A'.code + 27
        } else {
            item.code - 'a'.code + 1
        }
    }

    val rucksacks = readInput("Day03_test")
    var priority = 0

    for (rucksack in rucksacks) {
        val half = rucksack.length / 2
        val compartments = listOf(
            rucksack.substring(0, half).toHashSet(),
            rucksack.substring(half).toHashSet()
        )

        for (item in compartments[0]) {
            if (compartments[1].contains(item)) {
                priority += convertToPriority(item)
                break
            }
        }
    }

    println(priority)
}