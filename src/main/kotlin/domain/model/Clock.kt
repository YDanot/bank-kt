package domain.model

import java.time.LocalDateTime

interface Clock {

    fun now(): LocalDateTime
}