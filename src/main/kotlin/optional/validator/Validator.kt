package optional.validator

const val VALID_NUMBER_LENGTH = 3

fun interface StringValidator {
    fun validate(input: String): ValidationResult<String>
}

object Validator {
    fun validate(
        input: String,
        validationType: ValidationType,
    ): ValidationResult<String> {
        return StringChainValidator(
            validationType.validators,
        ).validate(
            input,
        )
    }
}
