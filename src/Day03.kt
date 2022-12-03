fun main() {

    fun convertToPriority(item: Char): Int {
        return if (item.isUpperCase()) {
            item.code - 'A'.code + 27
        } else {
            item.code - 'a'.code + 1
        }
    }

    fun part1(rucksacks: List<String>): Int {
        var priority = 0
        rucksacks.forEach { rucksack ->
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
        return priority
    }

    fun part2(rucksacks: List<String>): Int {
        var priorty = 0

        for (i in rucksacks.indices step 3) {
            val rucksack1 = rucksacks[i].toHashSet()
            val rucksack2 = rucksacks[i + 1].toHashSet()
            val rucksack3 = rucksacks[i + 2].toHashSet()

            val item = rucksack1.intersect(rucksack2).intersect(rucksack3)
            priorty += convertToPriority(item.first())
        }
        return priorty
    }

    val rucksacks = readInput("Day03_test")

    println(part1(rucksacks))
    println(part2(rucksacks))
}