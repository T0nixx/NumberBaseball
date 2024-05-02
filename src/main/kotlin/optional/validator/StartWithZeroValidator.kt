package optional.validator

object StartWithZeroValidator : StringValidator {
    override fun validate(input: String): ValidationResult<String> {
        if (input.first() == '0') {
            return ValidationFailure(
                input,
                "${input}은 0으로 시작합니다.",
            )
        }
        return ValidationSuccess(input)
    }
}
