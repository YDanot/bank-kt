package infra

import domain.model.Clock
import java.time.LocalDateTime

class SystemClock : Clock {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}