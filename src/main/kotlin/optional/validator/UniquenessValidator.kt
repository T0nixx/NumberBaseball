package optional.validator

val uniquenessValidator = StringValidator {
    if (it.toSet().size < VALID_NUMBER_LENGTH) {
        ValidationFailure(
            it,
            "${it}에 서로 같은 숫자가 있습니다.",
        )
    }
    else ValidationSuccess(it)
}
