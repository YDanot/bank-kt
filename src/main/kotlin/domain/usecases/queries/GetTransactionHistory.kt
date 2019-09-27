package domain.usecases.queries

import domain.model.account.AccountNumber
import domain.model.transaction.TransactionHistory
import domain.model.transaction.TransactionLogs


class GetTransactionHistory(private val transactionLogs: TransactionLogs) {

    fun of(number: AccountNumber): TransactionHistory? {
        return transactionLogs.logsOf(number)
    }

}