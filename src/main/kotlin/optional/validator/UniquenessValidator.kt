package optional.validator

object UniquenessValidator : StringValidator {
    override fun validate(input: String): ValidationResult<String> {
        if (input.toSet().size < VALID_NUMBER_LENGTH) {
            return ValidationFailure(
                input,
                "${input}에 서로 같은 숫자가 있습니다.",
            )
        }
        return ValidationSuccess(input)
    }
}
