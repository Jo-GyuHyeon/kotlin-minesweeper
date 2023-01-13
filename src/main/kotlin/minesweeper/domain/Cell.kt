package minesweeper.domain

sealed class Cell(val cellPosition: CellPosition) {

    class Mine(cellPosition: CellPosition) : Cell(cellPosition) {
        companion object {
            fun init(): Mine {
                val cellPosition = CellPosition(
                    xPosition = Position(0),
                    yPosition = Position(0)
                )

                return Mine(cellPosition)
            }
        }
    }

    class Blank(cellPosition: CellPosition, val mineCount: Int = 0, isOpen: Boolean = false) : Cell(cellPosition) {
        var isOpen = isOpen
            private set

        fun open(): Blank = Blank(cellPosition, mineCount, true)

        companion object {
            fun init(): Blank {
                val cellPosition = CellPosition(
                    xPosition = Position(0),
                    yPosition = Position(0)
                )

                return Blank(cellPosition)
            }
        }
    }

    fun isIn(cellPositions: List<CellPosition>): Boolean =
        cellPositions.any { cellPosition -> cellPosition == this.cellPosition }

    fun isMine(): Boolean =
        this is Mine

    fun mineCountIn(cellPositions: List<CellPosition>): Boolean =
        isIn(cellPositions) && isMine()

}
