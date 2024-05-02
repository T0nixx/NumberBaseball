package optional.validator

const val VALID_NUMBER_LENGTH = 3

fun interface StringValidator {
    fun validate(input: String): ValidationResult<String>
}

enum class InputType {
    NUMBER_STRING,
    USER_INPUT,
    ;

    companion object {
        fun getValidators(inputType: InputType): Array<StringValidator> {
            return when (inputType) {
                NUMBER_STRING -> {
                    arrayOf(
                        startWithZeroValidator,
                        uniquenessValidator,
                    )
                }

                USER_INPUT -> {
                    arrayOf(
                        isNumericValidator,
                        lengthValidator,
                        startWithZeroValidator,
                        uniquenessValidator,
                    )
                }
            }
        }
    }
}

object Validator {
    fun validate(
        input: String,
        inputType: InputType,
    ): ValidationResult<String> {
        return StringChainValidator(
            *InputType.getValidators(inputType),
        ).validate(
            input,
        )
    }
}
