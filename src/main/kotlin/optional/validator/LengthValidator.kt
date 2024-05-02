package optional.validator

object LengthValidator : StringValidator {
    override fun validate(input: String): ValidationResult<String> {
        if (input.length != VALID_NUMBER_LENGTH) {
            return ValidationFailure(
                input,
                "입력된 숫자 ${input}이 세자리 숫자가 아닙니다.",
            )
        }
        return ValidationSuccess(input)
    }
}
