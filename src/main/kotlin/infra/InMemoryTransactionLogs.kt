package infra

import domain.model.Money
import domain.model.account.AccountNumber
import domain.model.transaction.Transaction
import domain.model.transaction.TransactionHistory
import domain.model.transaction.TransactionLog
import domain.model.transaction.TransactionLogs


class InMemoryTransactionLogs() :
    TransactionLogs {
    private val historyByAccountNumber: MutableMap<AccountNumber, MutableList<TransactionLog>> = mutableMapOf()

    override fun log(number: AccountNumber, transaction: Transaction, balance: Money) {
        val log = TransactionLog(transaction, balance)
        historyByAccountNumber.getOrPut(number, { mutableListOf() }).add(log)
    }

    override fun logsOf(number: AccountNumber): TransactionHistory? {
        return historyByAccountNumber[number]?.toList()
    }
}