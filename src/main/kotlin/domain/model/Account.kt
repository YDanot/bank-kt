package domain.model

import java.time.LocalDateTime

typealias History = List<Transaction>

class Account(private val balance: Money = Money(0), private val history: History = listOf()) {

    fun balance(): Money {
        return balance
    }

    fun deposit(money: Money, now: LocalDateTime): Account {
        val newHistory = save(Transaction(TransactionType.DEPOSIT, now, money))
        return Account(money + balance, newHistory)
    }

    fun withdraw(money: Money, now: LocalDateTime): Account {
        val newHistory = save(Transaction(TransactionType.WITHDRAWAL, now, money))
        return Account(balance - money, newHistory)
    }

    private fun save(transaction: Transaction): History {
        val newHistory = history.toMutableList()
        newHistory.add(transaction)
        return newHistory
    }

    fun history(): History {
        return history.sortedByDescending { it.date }
    }

}
