package optional

fun main() {
    try {
        NumberBaseball(AnswerGenerator()).start()
    } catch (e: Exception) {
        println("처리되지 않은 예외입니다.")
        println(e.message)
        println(e.cause)
    } catch (e: Error) {
        println(e.message)
        println(e.cause)
    }
}
