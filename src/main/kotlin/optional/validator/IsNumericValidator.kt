package optional.validator

val isNumericValidator = StringValidator {
    if (it.toIntOrNull() == null) {
        ValidationFailure(
            it,
            "입력된 값 ${it}이 숫자가 아닙니다.",
        )
    }
    else ValidationSuccess(it)
}
