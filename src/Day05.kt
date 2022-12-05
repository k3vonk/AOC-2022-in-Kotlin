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

    fun topOfEachStack(stacks: List<LinkedList<Char>>) =
        stacks.map { it.peek() }.joinToString(separator = "")

    val data = readInput("Day05_test")

    val crates = data.take(8)
    val stacksPart1 = createStacks(crates)
    val stacksPart2 = createStacks(crates)
    val moves = data.takeLast(data.size - 10) // I like magic numbers

    for (move in moves) {
        val pattern = "[0-9]+".toRegex()
        val digits = pattern.findAll(move).map { it.value.toInt() }.toList()

        val numberOfCrates = digits.first()
        val ( from, to ) = digits.takeLast(2)

        val tempStack = LinkedList<Char>()
        for(i in 0 until numberOfCrates) {
            val item = stacksPart1[from - 1].pop()
            val itemInStack2 = stacksPart2[from - 1].pop()
            tempStack.add(itemInStack2)
            stacksPart1[to - 1].push(item)
        }
        stacksPart2[to - 1].addAll(0, tempStack)
    }

    println(topOfEachStack(stacksPart1))
    println(topOfEachStack(stacksPart2))
}