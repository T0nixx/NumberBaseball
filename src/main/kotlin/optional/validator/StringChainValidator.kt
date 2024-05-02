package optional.validator

class StringChainValidator(private val validators: Array<StringValidator>) :
    StringValidator {
    override fun validate(input: String): ValidationResult<String> {
        var result: ValidationResult<String> = ValidationSuccess(input)
        for (validator in validators) {
            result = validator.validate(input)
            if (result is ValidationFailure) return result
        }
        return result
    }
}
