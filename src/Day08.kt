fun main() {

    fun <E: Any> printPrettyGrid(grid: MutableList<MutableList<E>>) {
        println()
        grid.forEach {
            println(it)
        }
    }

    fun parseInputIntoGrid(input: List<String>, grid: MutableList<MutableList<Char>>) {
        input.forEach {
            grid.add(it.toMutableList())
        }
    }

    fun generateVisibilityGrid(grid: MutableList<MutableList<Char>>): MutableList<MutableList<Int>> {
        val rows = grid.size
        val columns = grid[0].size

        return MutableList(rows) { MutableList(columns) { 0 } } // 0 = invisible
    }

    fun cleanUpViewScoreGrid(treeGrid: MutableList<MutableList<Char>>, grid: MutableList<MutableList<Int>>) {
        for (i in grid.indices) {
            // rows
            grid[0][i] = 0
            grid[grid.lastIndex][i] = 0 // last row

            // columns
            grid[i][0] = 0 // first column
            grid[i][grid[0].lastIndex] = 0 // last column
        }

        val max = grid.maxBy { it.max() }.max()
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] < max) grid[i][j] = 0
                else grid[i][j] = treeGrid[i][j].digitToInt()
            }
        }
    }

    fun countVisibleTrees(visibilityGrid: MutableList<MutableList<Int>>): Int {
        return visibilityGrid.sumOf { li ->
            li.count { it == 1 }
        }
    }

    fun part1(treeGrid: MutableList<MutableList<Char>>, pointGrid: MutableList<MutableList<Int>>): Int {
        val visibilityGrid = generateVisibilityGrid(treeGrid)

        for (rowIndex in 0 until treeGrid.size) {
            var leftSide = 0
            var rightSide = 0
            for (columnIndex in 0 until treeGrid[0].size) {
                if (treeGrid[rowIndex][columnIndex].code > leftSide) {
                    visibilityGrid[rowIndex][columnIndex] = 1
                    leftSide = treeGrid[rowIndex][columnIndex].code

                    pointGrid[rowIndex][columnIndex] += 1
                }

                val reverseColumnIndex = treeGrid[0].size - 1 - columnIndex
                if (treeGrid[rowIndex][reverseColumnIndex].code > rightSide) {
                    visibilityGrid[rowIndex][reverseColumnIndex] = 1
                    rightSide = treeGrid[rowIndex][reverseColumnIndex].code

                    pointGrid[rowIndex][reverseColumnIndex] += 1
                }
            }
        }

        for (columnIndex in 0 until treeGrid[0].size) {
            var topSide = 0
            var bottomSide = 0
            for (rowIndex in 0 until treeGrid.size) {
                // view from top
                if (treeGrid[rowIndex][columnIndex].code > topSide) {
                    visibilityGrid[rowIndex][columnIndex] = 1
                    topSide = treeGrid[rowIndex][columnIndex].code

                    pointGrid[rowIndex][columnIndex] += 1
                }

                // view from bottom
                val reverseRow = treeGrid.size - 1 - rowIndex
                if(treeGrid[reverseRow][columnIndex].code > bottomSide) {
                    visibilityGrid[reverseRow][columnIndex] = 1
                    bottomSide = treeGrid[reverseRow][columnIndex].code

                    pointGrid[reverseRow][columnIndex] += 1
                }
            }
        }

        return countVisibleTrees(visibilityGrid)
    }

    fun part2(treeGrid: MutableList<MutableList<Char>>, pointGrid: MutableList<MutableList<Int>>): Int {
        cleanUpViewScoreGrid(treeGrid, pointGrid)
//        val visibilityGrid = generateVisibilityGrid(treeGrid)
        for (rowIndex in 0 until pointGrid.size) {
            for (columnIndex in 0 until pointGrid[0].size) {

//                if (treeGrid[rowIndex][columnIndex].code > leftSide) {
//                    pointGrid[rowIndex][columnIndex] = 1
//                    leftSide = treeGrid[rowIndex][columnIndex].code
//                }

//                val reverseColumnIndex = treeGrid[0].size - 1 - columnIndex
//                if (treeGrid[rowIndex][reverseColumnIndex].code > rightSide) {
//                    pointGrid[rowIndex][reverseColumnIndex] = 1
//                    rightSide = treeGrid[rowIndex][reverseColumnIndex].code
//                }
            }
        }
//
//        for (columnIndex in 0 until treeGrid[0].size) {
//            var topSide = 0
//            var bottomSide = 0
//            for (rowIndex in 0 until treeGrid.size) {
//                // view from top
//                if (treeGrid[rowIndex][columnIndex].code > topSide) {
//                    pointGrid[rowIndex][columnIndex] = 1
//                    topSide = treeGrid[rowIndex][columnIndex].code
//                }
//
//                // view from bottom
//                val reverseRow = treeGrid.size - 1 - rowIndex
//                if(treeGrid[reverseRow][columnIndex].code > bottomSide) {
//                    pointGrid[reverseRow][columnIndex] = 1
//                    bottomSide = treeGrid[reverseRow][columnIndex].code
//                }
//            }
//        }
        printPrettyGrid(pointGrid)

        return countVisibleTrees(pointGrid)
    }

    val input = readInput("Day08_mock")
    val treeGrid = mutableListOf<MutableList<Char>>()

    parseInputIntoGrid(input, treeGrid)
    printPrettyGrid(treeGrid)
    val viewPoint = generateVisibilityGrid(treeGrid)

    println(part1(treeGrid, viewPoint))
    println(part2(treeGrid, viewPoint))
}