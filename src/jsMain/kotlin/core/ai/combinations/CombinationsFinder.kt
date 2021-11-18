package core.ai.combinations

class CombinationsFinder(private val combinationPatterns: CombinationPatterns) {

    fun findNoughtsCombinations(line: String): List<GeneralCombination?> {
        return combinationPatterns.noughtsPatternsToCombinations.keys
                .asSequence()
                .filter { pattern: Regex -> line.contains(pattern) }
                .map { key: Regex -> combinationPatterns.noughtsPatternsToCombinations[key] }
                .toList();
    }

    fun findCrossesCombinations(line: String): List<GeneralCombination?> {
        return combinationPatterns.crossesPatternsToCombinations.keys
                .asSequence()
                .filter { pattern: Regex -> line.contains(pattern) }
                .map { key: Regex -> combinationPatterns.crossesPatternsToCombinations[key] }
                .toList();
    }

}
