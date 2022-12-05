import java.util.LinkedList

fun main() {

    fun createStacks(crates: List<String>): List<LinkedList<Char>> {
        val stacks: List<LinkedList<Char>> = List(9) { LinkedList<Char>() }
        for (crate in crates) {
            val arr = crate.toCharArray()
            for (i in arr.indices) {
                if (arr[i].isLetter()) {
                    stacks[(i-1)/4].add(arr[i])
                }
            }
        }

        return stacks
    }

    fun part1(moves: List<String>, stacks: List<LinkedList<Char>>): String {
        for (move in moves) {
            val pattern = "[0-9]+".toRegex()
            val digits = pattern.findAll(move).map { it.value.toInt() }.toList()

            val numberOfCrates = digits.first()
            val transition = digits.takeLast(2)

            for(i in 0 until numberOfCrates) {
                val item = stacks[transition[0] - 1].pop()
                stacks[transition[1] - 1].push(item)
            }
        }

        return stacks.map { it.peek() }.joinToString(separator = "")
    }
    val data = readInput("Day05_test")

    val crates = data.take(8)
    val stacks = createStacks(crates)
    val moves = data.takeLast(data.size - 10) // I like magic numbers

    println(part1(moves, stacks))
}
