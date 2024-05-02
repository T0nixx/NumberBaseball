package requisite

interface ValidationResult<T>

data class ValidationFailure<T>(
    val data: T?,
    val message: String,
) : ValidationResult<T>

data class ValidationSuccess<T>(val data: T) : ValidationResult<T>

const val VALID_NUMBER_LENGTH = 3

class Validator {
    companion object {
        fun validateNumberString(
            numberString: String,
        ): ValidationResult<String> {
            if (numberString.toSet().size != numberString.length) {
                return ValidationFailure(
                    numberString,
                    "${numberString}에 서로 같은 숫자가 있습니다.",
                )
            }

            if (numberString.any { it == '0' }) {
                return ValidationFailure(
                    numberString,
                    "${numberString}에 0이 포함되어 있습니다.",
                )
            }
            return ValidationSuccess(numberString)
        }

        fun validateUserInput(numberInput: String?): ValidationResult<String> {
            if (numberInput.isNullOrBlank()) {
                return ValidationFailure(numberInput, "입력된 값이 null이거나 없습니다.")
            }
            if (numberInput.toIntOrNull() == null) {
                return ValidationFailure(
                    numberInput,
                    "입력된 값 ${numberInput}이 정수가 아닙니다.",
                )
            }

            if (numberInput.length != VALID_NUMBER_LENGTH) {
                return ValidationFailure(
                    numberInput,
                    "입력된 숫자 ${numberInput}이 세자리 숫자가 아닙니다.",
                )
            }

            return validateNumberString(numberInput)
        }
    }
}
