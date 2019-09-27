package infra

import domain.model.*
import domain.model.account.AccountNumber
import domain.model.transaction.Transaction
import domain.model.transaction.TransactionHistory
import domain.model.transaction.TransactionLog
import domain.model.transaction.TransactionLogs


class InMemoryTransactionLogs(private val history: MutableMap<AccountNumber, MutableList<TransactionLog>> = mutableMapOf()) :
    TransactionLogs {

    override fun log(number: AccountNumber, transaction: Transaction, balance: Money) {
        val list = history.getOrPut(number, { mutableListOf() })
        list.add(TransactionLog(transaction, balance))
    }

    override fun logsOf(number: AccountNumber): TransactionHistory? {
        return history[number]?.toList()
    }
}