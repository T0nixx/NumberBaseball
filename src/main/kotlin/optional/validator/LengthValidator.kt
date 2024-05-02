package optional.validator

val lengthValidator =
    StringValidator {
        if (it.length != VALID_NUMBER_LENGTH) {
            ValidationFailure(
                it,
                "입력된 숫자 ${it}이 세자리 숫자가 아닙니다.",
            )
        } else {
            ValidationSuccess(it)
        }
    }
