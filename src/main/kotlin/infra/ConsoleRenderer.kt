package infra

import domain.model.*
import domain.usecases.command.Deposit
import domain.usecases.command.Withdraw
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsoleRenderer {

    fun statement(history: History): String {
        var account = Account()
        val headers = "date || credit || debit || balance"
        val statements = mutableListOf<String>()
        history.sortedBy { it.date }.forEach {
            account = replay(it, account)
            statements.add(render(it, account.balance()))
        }

        return headers + "\n" + statements.reversed().joinToString("\n")
    }

    private fun render(transaction: Transaction, balance: Money): String {
        return when (transaction.type) {
            TransactionType.DEPOSIT -> "${transaction.date.render()} || ${transaction.amount.render()} || || ${balance.render()}"
            TransactionType.WITHDRAWAL -> "${transaction.date.render()} || || ${transaction.amount.render()} || ${balance.render()}"
        }
    }

    private fun Money.render(): String {
        return NumberFormat.getCurrencyInstance().format(cents.toDouble() / 100)
    }

    fun LocalDateTime.render(): String {
        return DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm").format(this)
    }

    private fun replay(transaction: Transaction, account: Account): Account {
        return when (transaction.type) {
            TransactionType.DEPOSIT -> Deposit(transaction.amount, transaction.date).on(account)
            TransactionType.WITHDRAWAL -> Withdraw(transaction.amount, transaction.date).from(account)
        }
    }
}