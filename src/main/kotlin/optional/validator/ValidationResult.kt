package optional.validator

interface ValidationResult<T>

data class ValidationFailure<T>(
    val data: T?,
    val message: String,
) : ValidationResult<T>

data class ValidationSuccess<T>(val data: T) : ValidationResult<T>
