package domain.model.transaction

import domain.model.Money
import java.time.LocalDateTime

data class Transaction(val type: TransactionType, val date: LocalDateTime, val amount: Money) {

    override fun toString(): String {
        return "$type of $amount the $date"
    }
}
