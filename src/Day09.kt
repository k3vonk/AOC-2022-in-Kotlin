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
        val head = Point(0.0, 0.0)
        val tail = Point(0.0, 0.0)
        val visited = mutableListOf(Point(0.0, 0.0))

        for (i in input) {
            val (direction, count) = i.split(" ")

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

//                println("head: $head, tail: $tail")
            }
        }

        return visited.size
    }

    fun part2(input: List<String>): Int {
        val ropeknots = MutableList(10) { Point(0.0, 0.0) }
        val visited = mutableListOf(Point(0.0, 0.0))

        for (i in input) {
            val (direction, count) = i.split(" ")

            for (walk in 0 until count.toInt()) {
                val directionalPoint = directions[direction]!!

                ropeknots.forEachIndexed { index, currPoint ->

                    if(index == 0) {
                        currPoint.plus(directionalPoint)
                    } else {
                        val prevKnot = ropeknots[index - 1]
                        if (isTwoGridApart(prevKnot, currPoint)) {
                            if (prevKnot.x == currPoint.x || prevKnot.y == currPoint.y) {
                                val newDirection = Point((prevKnot.x - currPoint.x)/2, (prevKnot.y - currPoint.y)/2)
                                currPoint.plus(newDirection)
                            } else {
                                moveTailBasedOnDirection(prevKnot, currPoint)
                            }

                            if(!visited.contains(Point(currPoint.x, currPoint.y)) && index == 9) {
                                visited.add(Point(currPoint.x, currPoint.y))
                            }
                        }
                    }
                }
            }
        }

        return visited.size
    }

    val inputs = readInput("Day09_test")


    println(part1(inputs))
    println(part2(inputs))
}