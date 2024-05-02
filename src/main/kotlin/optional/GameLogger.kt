package optional

data class GameLog(
    val gameCount: Int,
    val tryCount: Int,
)

object GameLogger {
    private val logs = ArrayList<GameLog>()

    private fun getLogs(): List<GameLog> {
        return logs
    }

    fun showLogs() {
        println("< 게임 기록 보기 >")
        getLogs().run {
            if (isEmpty()) println("기록된 게임이 없습니다.")
            forEach { (gameCount, tryCount) ->
                println("${gameCount}번째 게임 : 시도 횟수 - $tryCount")
            }
        }
    }

    fun logGame(log: GameLog) {
        logs.add(log)
    }
}
