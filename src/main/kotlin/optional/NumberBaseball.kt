package optional

import kotlin.random.Random

const val MESSAGE_ON_THREE_STRIKE = "정답입니다."

class NumberBaseball {
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

    private fun playGame(gameCount: Int) {
        println("< 게임을 시작합니다. >")
        val answer = generateAnswer()
        var tryCount = 0
        while (true) {
            var validationResult: ValidationResult<String>? = null
            while (validationResult is ValidationSuccess == false) {
                if (validationResult is ValidationFailure) {
                    println(validationResult.message)
                }
                println("정답이라고 생각하는 3자리 숫자를 입력해주세요")
                println("0은 백의 자리의 수에 들어갈 수 없고 각 자릿수는 중복되어서는 안됩니다.")
                val userInput = readlnOrNull()

                validationResult = Validator.validateUserInput(userInput)
            }

            tryCount += 1
            val userAnswer = validationResult.data
            val umpire = Umpire(answer, userAnswer)
            if (umpire.isThreeStrike) {
                println(MESSAGE_ON_THREE_STRIKE)
                break
            }
            val hint = umpire.getHint()
            println(hint)
        }
        GameLogger.logGame(GameLog(gameCount = gameCount, tryCount = tryCount))
    }

    fun start() {
        var gameCount = 0
        println("환영합니다! 원하시는 번호를 입력해주세요.")

        while (true) {
            println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기")
            val selectedMenu = readlnOrNull()
            when (selectedMenu) {
                "1" -> playGame(++gameCount)
                "2" -> GameLogger.showLogs()
                "3" -> {
                    println("< 숫자 야구 게임을 종료합니다 >")
                    break
                }

                else -> println("올바른 숫자를 입력해주세요!")
            }
        }
    }
}
