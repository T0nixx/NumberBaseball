package optional

import optional.validator.InputType
import optional.validator.ValidationFailure
import optional.validator.Validator
import kotlin.random.Random

class AnswerGenerator {
    fun generateAnswer(): String {
        var randomNumber = Random.nextInt(100, 999)
        while (Validator.validate(
                randomNumber.toString(),
                InputType.NUMBER_STRING,
            ) is ValidationFailure
        ) {
            randomNumber = Random.nextInt(100, 999)
        }

        return randomNumber.toString()
    }
}
