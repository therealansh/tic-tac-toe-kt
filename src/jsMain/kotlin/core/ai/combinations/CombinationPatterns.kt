package core.ai.combinations

class CombinationPatterns {
    val noughtsPatternsToCombinations: Map<Regex, GeneralCombination>
    val crossesPatternsToCombinations: Map<Regex, GeneralCombination>

    companion object {
        val FIVE_NOUGHTS_PATTERN  = """00000""".toRegex()
        val STRAIGHT_FOUR_NOUGHTS_PATTERN = """ 0000 """.toRegex()
        val FOUR_NOUGHTS_PATTERN = """( 0000x)|(x0000 )|(^0000 )|( 0000$)|( 0000#)|(#0000 )""".toRegex()
        val THREE_NOUGHTS_PATTERN = """ 000 """.toRegex()
        val BROKEN_THREE_NOUGHTS_PATTERN = """( 0 00 )|( 00 0 )""".toRegex()
        val TWO_NOUGHTS_PATTERN = """ 00 """.toRegex()
        val ONE_NOUGHT_PATTERN = """ 0 """.toRegex()

        val FIVE_CROSSES_PATTERN = """xxxxx""".toRegex()
        val STRAIGHT_FOUR_CROSSES_PATTERN = """ xxxx """.toRegex()
        val FOUR_CROSSES_PATTERN = """( xxxx0)|(0xxxx )|(^xxxx )|( xxxx$)|( xxxx#)|(#xxxx )""".toRegex()
        val THREE_CROSSES_PATTERN = """ xxx """.toRegex()
        val BROKEN_THREE_CROSSES_PATTERN = """( x xx )|( xx x )""".toRegex()
        val TWO_CROSSES_PATTERN = """ xx """.toRegex()
        val ONE_CROSS_PATTERN = """ x """.toRegex()
    }

    init {
        noughtsPatternsToCombinations = hashMapOf(
                FIVE_NOUGHTS_PATTERN to GeneralCombination.FIVE,
                STRAIGHT_FOUR_NOUGHTS_PATTERN to GeneralCombination.STRAIGHT_FOUR,
                FOUR_NOUGHTS_PATTERN to GeneralCombination.FOUR,
                THREE_NOUGHTS_PATTERN to GeneralCombination.THREE,
                BROKEN_THREE_NOUGHTS_PATTERN to GeneralCombination.BROKEN_THREE,
                TWO_NOUGHTS_PATTERN to GeneralCombination.TWO,
                ONE_NOUGHT_PATTERN to GeneralCombination.ONE
        )

        crossesPatternsToCombinations = hashMapOf(
                FIVE_CROSSES_PATTERN to GeneralCombination.FIVE,
                STRAIGHT_FOUR_CROSSES_PATTERN to GeneralCombination.STRAIGHT_FOUR,
                FOUR_CROSSES_PATTERN to GeneralCombination.FOUR,
                THREE_CROSSES_PATTERN to GeneralCombination.THREE,
                BROKEN_THREE_CROSSES_PATTERN to GeneralCombination.BROKEN_THREE,
                TWO_CROSSES_PATTERN to GeneralCombination.TWO,
                ONE_CROSS_PATTERN to GeneralCombination.ONE
        )
    }
}
