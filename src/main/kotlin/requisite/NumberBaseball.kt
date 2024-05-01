package requisite

import kotlin.random.Random

const val MESSAGE_ON_THREE_STRIKE = "정답입니다."

class NumberBaseball {
    init {
        println("< 게임을 시작합니다. >")
        val answer = generateAnswer()
        playGame(answer)
    }

    private fun generateAnswer(): String {
        var randomNumber = Random.nextInt(100, 999)

        while (Validator.validateNumberString(
                randomNumber.toString(),
            ) is ValidationFailure
        ) {
            randomNumber = Random.nextInt(100, 999)
        }

        return randomNumber.toString()
    }

    private fun playGame(answer: String) {
        while (true) {
            var validationResult: ValidationResult<String>? = null
            while (validationResult is ValidationSuccess == false) {
                if (validationResult is ValidationFailure) {
                    println(validationResult.message)
                }
                println("정답이라고 생각하는 3자리 숫자를 입력해주세요")
                val userInput = readlnOrNull()

                validationResult = Validator.validateUserInput(userInput)
            }

            val userAnswer = validationResult.data
            val umpire = Umpire(answer, userAnswer)

            if (umpire.isThreeStrike) {
                println(MESSAGE_ON_THREE_STRIKE)
                break
            }

            val hint = umpire.getHint()
            println(hint)
        }
    }
}
