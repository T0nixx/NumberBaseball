package optional

import optional.validator.ValidationType
import optional.validator.ValidationFailure
import optional.validator.Validator
import kotlin.random.Random

class AnswerGenerator {
    fun generateAnswer(): String {
        var randomNumber = Random.nextInt(100, 999)
        while (Validator.validate(
                randomNumber.toString(),
                ValidationType.NUMBER_STRING,
            ) is ValidationFailure
        ) {
            randomNumber = Random.nextInt(100, 999)
        }

        return randomNumber.toString()
    }
}
