package optional.validator

val startWithZeroValidator = StringValidator {
    if (it.first() == '0') {
        ValidationFailure(
            it,
            "${it}은 0으로 시작합니다.",
        )
    }
    else ValidationSuccess(it)
}
