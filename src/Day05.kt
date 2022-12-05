import java.util.LinkedList

fun main() {

    fun createStacks(crates: List<String>): List<LinkedList<Char>> {
        val stacks: List<LinkedList<Char>> = List(9) { LinkedList<Char>() }
        for (crate in crates) {
            val arr = crate.toCharArray()
            for (i in arr.indices) {
                if (arr[i].isLetter()) {
                    stacks[(i-1)/4].push(arr[i])
                }
            }
        }

        return stacks
    }
    val data = readInput("Day05_test")

    val crates = data.take(8)
    val stacks = createStacks(crates)

    println(stacks)
}