package infra


import domain.model.Money
import domain.model.transaction.Transaction
import domain.model.transaction.TransactionHistory
import domain.model.transaction.TransactionType
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsoleRenderer {

    fun statement(history: TransactionHistory): String {
        val headers = "date || credit || debit || balance"
        val statements =
            history.sortedBy { it.transaction.date }.map {
                render(it.transaction, it.accountBalance)
            }

        return headers + "\n" + statements.reversed().joinToString("\n")
    }

    private fun render(transaction: Transaction, balance: Money): String {
        return when (transaction.type) {
            TransactionType.DEPOSIT, TransactionType.INWARD_TRANSFER ->
                "${transaction.date.render()} || ${transaction.amount.render()} || || ${balance.render()}"
            TransactionType.WITHDRAWAL, TransactionType.OUTGOING_TRANSFER ->
                "${transaction.date.render()} || || ${transaction.amount.render()} || ${balance.render()}"
        }
    }

    private fun Money.render(): String {
        return NumberFormat.getCurrencyInstance().format(cents.toDouble() / 100)
    }

    fun LocalDateTime.render(): String {
        return DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm").format(this)
    }
}