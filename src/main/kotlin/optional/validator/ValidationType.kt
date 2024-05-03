package optional.validator

enum class ValidationType(val validators: Array<StringValidator>) {
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
