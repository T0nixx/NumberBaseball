package optional.validator

const val VALID_NUMBER_LENGTH = 3

fun interface StringValidator {
    fun validate(input: String): ValidationResult<String>
}

enum class InputType(val validators: Array<StringValidator>) {
    NUMBER_STRING(
        arrayOf(
            startWithZeroValidator,
            uniquenessValidator,
        ),
    ),
    USER_INPUT(
        arrayOf(
            isIntegerValidator,
            lengthValidator,
            startWithZeroValidator,
            uniquenessValidator,
        ),
    ),
    ;

    companion object
}

object Validator {
    fun validate(
        input: String,
        inputType: InputType,
    ): ValidationResult<String> {
        return StringChainValidator(
            *inputType.validators,
        ).validate(
            input,
        )
    }
}
