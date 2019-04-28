package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0
    val secretMutableList = secret.toMutableList()
    val guessMutableList = guess.toMutableList()
    val list = ArrayList<Int>()
    for (i in guessMutableList.indices)
        if (guessMutableList[i] == secretMutableList[i]) {
            rightPosition += 1
            list.add(i)
        }
    removeMatchedPositions(list, guessMutableList, secretMutableList)
    if (guessMutableList.isNotEmpty())
        for (i in guessMutableList.indices)
            if (secretMutableList.contains(guessMutableList[i])) {
                secretMutableList.remove(guessMutableList[i])
                wrongPosition += 1
            }

    return Evaluation(rightPosition, wrongPosition)
}

fun removeMatchedPositions(matchedIndices: MutableList<Int>, guess: MutableList<Char>, secret: MutableList<Char>) {
    if (matchedIndices.isNotEmpty())
        for (i in matchedIndices.reversed()) {
            guess.removeAt(i)
            secret.removeAt(i)
        }
}
