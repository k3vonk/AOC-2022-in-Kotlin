import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(var x: Double, var y: Double) {
    operator fun plus(other: Point) {
        this.x += other.x
        this.y += other.y
    }
}

val directions = mapOf(
    "R" to Point(1.0, 0.0),
    "L" to Point(-1.0, 0.0),
    "U" to Point(0.0, 1.0),
    "D" to Point(0.0, -1.0)
)

fun main() {

    fun isTwoGridApart(head: Point, tail: Point): Boolean {
        val distance =  sqrt((tail.x - head.x).pow(2) + (tail.y - head.y).pow(2))
        return floor(distance) == 2.0
    }

    fun moveTailBasedOnDirection(head: Point, tail: Point) {
        val dx = head.x - tail.x
        val dy = head.y - tail.y

        if (dx > 0) {
            tail.plus(directions["R"]!!)
        } else if (dx < 0) {
            tail.plus(directions["L"]!!)
        }

        if (dy > 0) {
            tail.plus(directions["U"]!!)
        } else if (dy < 0) {
            tail.plus(directions["D"]!!)
        }
    }

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val inputs = readInput("Day09_test")
    var head = Point(0.0, 0.0)
    var tail = Point(0.0, 0.0)
    val visited = mutableListOf(Point(0.0, 0.0))

    for (input in inputs) {
        val (direction, count) = input.split(" ")

        for (walk in 0 until count.toInt()) {
            val directionalPoint = directions[direction]!!
            head.plus(directionalPoint)

            if (isTwoGridApart(head, tail)) {
                if (head.x == tail.x || head.y == tail.y) {
                    tail.plus(directionalPoint)
                } else {
                    moveTailBasedOnDirection(head, tail)
                }

                if(!visited.contains(Point(tail.x, tail.y))) {
                    visited.add(Point(tail.x, tail.y))
                }
            }

            println("head: $head, tail: $tail")
        }
        println(visited.size)
    }

    println(part1(inputs))
    println(part2(inputs))
}