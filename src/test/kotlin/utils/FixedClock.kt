package utils

import domain.model.Clock
import java.time.LocalDateTime

class FixedClock(private val date: LocalDateTime) : Clock {

    override fun now(): LocalDateTime {
        return date
    }
}