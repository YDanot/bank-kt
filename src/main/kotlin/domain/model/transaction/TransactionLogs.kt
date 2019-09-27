package domain.model.transaction

import domain.model.Money
import domain.model.account.AccountNumber


typealias TransactionHistory = List<TransactionLog>

interface TransactionLogs {

    fun log(number: AccountNumber, transaction: Transaction, balance: Money)

    fun logsOf(number: AccountNumber): TransactionHistory?

}
