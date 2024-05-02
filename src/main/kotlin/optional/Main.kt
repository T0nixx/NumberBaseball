package optional

fun main() {
    try {
        NumberBaseball(AnswerGenerator()).start()
    } catch (e: Error) {
        println("처리되지 않은 예외입니다.")
        println(e.message)
        println(e.cause)
    }
}
