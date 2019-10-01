package domain.model.transaction

import domain.model.Money
import java.time.LocalDateTime

data class Transaction(
    val type: TransactionType,
    val date: LocalDateTime, val amount: Money,
    val transactionId: TransactionId = TransactionId()) {

    override fun toString(): String {
        return "$transactionId : $type of $amount the $date"
    }
}
