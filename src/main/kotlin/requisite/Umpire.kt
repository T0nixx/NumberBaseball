package requisite

class Umpire(answer: String, userAnswer: String) {
    private var strike: Int = 0
    private var ball: Int = 0

    init {
        userAnswer.forEachIndexed { i, ch ->
            if (ch == answer[i]) {
                strike++
            } else if (ch in answer) {
                ball++
            }
        }
    }

    val isThreeStrike: Boolean = strike == 3

    fun getHint(): String {
        if (strike == 0 && ball == 0) {
            return "Nothing"
        }
        val strikeHint = if (strike == 0) "" else "$strike 스트라이크"
        val ballHint = if (ball == 0) "" else "$ball 볼"
        return if (strikeHint.isBlank()) ballHint else "$strikeHint $ballHint"
    }
}
