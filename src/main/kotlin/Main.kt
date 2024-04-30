import kotlin.random.Random

interface ValidationResult<T>

data class ValidationFailure<T>(
    val data: T?,
    val message: String
) : ValidationResult<T>

data class ValidationSuccess<T>(val data: T) : ValidationResult<T>

fun validateThreeDigitNumber(numberString: String): ValidationResult<String> {
    if (numberString.toSet().size != numberString.length) {
        return ValidationFailure(
            numberString,
            "${numberString}에 서로 같은 숫자가 있습니다."
        )
    }

    numberString.forEach {
        if (it == '0') return ValidationFailure(
            numberString,
            "${numberString}에 0이 포함되어 있습니다."
        )
    }
    return ValidationSuccess(numberString)
}

fun validateUserInput(numberInput: String): ValidationResult<String> {
    if (numberInput.toDoubleOrNull() == null) {
        return ValidationFailure(
            numberInput,
            "입력된 값 ${numberInput}이 숫자가 아닙니다."
        )
    }

    if (numberInput.length != 3) {
        return ValidationFailure(
            numberInput,
            "입력된 숫자 ${numberInput}이 세자리 숫자가 아닙니다."
        )
    }

    return validateThreeDigitNumber(numberInput)
}

fun generateAnswer(): String {
    var randomNumber = Random.nextInt(100, 999)

    while (validateThreeDigitNumber(randomNumber.toString()) is ValidationFailure) {
        randomNumber = Random.nextInt(100, 999)
    }

    return randomNumber.toString()
}

class ABSResult {
    private var STRIKE: Int = 0
    private var BALL: Int = 0
    val CORRECT_MESSAGE = "정답입니다."

    fun strike() {
        STRIKE += 1
    }

    fun ball() {
        BALL += 1
    }

    fun isThreeStrike(): Boolean = STRIKE == 3

    fun getHint(): String {
        if (STRIKE == 0 && BALL == 0) {
            return "Nothing"
        }
        val strikeHint = if (STRIKE == 0) "" else "$STRIKE 스트라이크"
        val ballHint = if (BALL == 0) "" else "$BALL 볼"
        return if (strikeHint.isBlank()) ballHint else "$strikeHint $ballHint"
    }
}

fun abs(answer: String, userAnswer: String): ABSResult {
    val absResult = ABSResult()
    for (i in 0..2) {
        for (j in 0..2) {
            if (i == j && answer[i] == userAnswer[j]) {
                absResult.strike()
                continue
            }
            if (answer[i] == userAnswer[j]) {
                absResult.ball()
            }
        }
    }
    return absResult
}

fun playGame(answer: String) {
    while (true) {
        var validationResult: ValidationResult<String>? = null
        while (validationResult is ValidationSuccess == false) {
            if (validationResult is ValidationFailure) {
                println(validationResult.message)
            }
            println("정답이라고 생각하는 3자리 숫자를 입력해주세요")
            val userInput = readlnOrNull()
            if (userInput.isNullOrBlank()) {
                validationResult =
                    ValidationFailure(userInput, "입력된 값이 null이거나 없습니다.")
                continue
            }
            validationResult = validateUserInput(userInput)
        }

        val userAnswer = validationResult.data
        val absResult = abs(answer, userAnswer)
        if (absResult.isThreeStrike()) {
            println(absResult.CORRECT_MESSAGE)
            break
        }

        val hint = absResult.getHint()
        println(hint)
    }
}

fun main() {
    println("< 게임을 시작합니다. >")
    val answer = generateAnswer()
    playGame(answer)
}
