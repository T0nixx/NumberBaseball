package optional.validator

val isIntegerValidator = StringValidator {
    if (it.toIntOrNull() == null) {
        ValidationFailure(
            it,
            "입력된 값 ${it}이 정수가 아닙니다.",
        )
    }
    else ValidationSuccess(it)
}
