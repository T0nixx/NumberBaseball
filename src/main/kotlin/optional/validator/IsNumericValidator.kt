package optional.validator

object IsNumericValidator : StringValidator {
    override fun validate(input: String): ValidationResult<String> {
        if (input.toIntOrNull() == null) {
            return ValidationFailure(
                input,
                "입력된 값 ${input}이 숫자가 아닙니다.",
            )
        }
        return ValidationSuccess(input)
    }
}
