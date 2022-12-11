fun main() {
    val gameResponseScore = mapOf(
        "R" to 1,
        "P" to 2,
        "S" to 3
    )

    val gameResult = mapOf(
        "R" to "R" to 3,
        "R" to "P" to 6,
        "R" to "S" to 0,
        "P" to "R" to 0,
        "P" to "P" to 3,
        "P" to "S" to 6,
        "S" to "R" to 6,
        "S" to "P" to 0,
        "S" to "S" to 3
    )

    val player1Action = mapOf(
        "A" to "R",
        "B" to "P",
        "C" to "S"
    )

    val player2Action = mapOf(
        "X" to "R",
        "Y" to "P",
        "Z" to "S"
    )

    val part2Combo = mapOf(
        "R" to "Z" to "P",
        "R" to "Y" to "R",
        "R" to "X" to "S",
        "P" to "Z" to "S",
        "P" to "Y" to "P",
        "P" to "X" to "R",
        "S" to "Z" to "R",
        "S" to "Y" to "S",
        "S" to "X" to "P"
    )

    fun part1(input: List<String>): Int {
        var score = 0
        for (game in input) {
            val (player1, player2) = game.split(" ")

            val a1 = player1Action[player1]!!
            val a2 = player2Action[player2]!!
            val gameScore = gameResult[a1 to a2]!! + gameResponseScore[a2]!!
            score += gameScore
        }
        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        for (game in input) {
            val (player1, gameFutureState) = game.split(" ")
            val a1 = player1Action[player1]!!
            // figure out what player2 plays
            val a2 = part2Combo[a1 to gameFutureState]!!
            val gameScore = gameResult[a1 to a2]!! + gameResponseScore[a2]!!
            score += gameScore
        }
        return score
    }

    val input = readInput("Day02_test")
    println(part1(input))
    println(part2(input))
}