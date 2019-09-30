package domain.usecases.command


import domain.model.Money
import domain.model.transaction.Transaction
import domain.model.transaction.TransactionLogs
import domain.model.transaction.TransactionType
import domain.model.account.Account
import infra.InMemoryTransactionLogs
import infra.SystemClock
import java.time.LocalDateTime

class Deposit(
    private val amount: Money,
    private val time: LocalDateTime = SystemClock().now(),
    private val transactionLogs: TransactionLogs = InMemoryTransactionLogs()
) {

    fun on(account: Account): Account {
        val depositAccount = account.deposit(amount)
        transactionLogs.log(account.number(),
            Transaction(TransactionType.DEPOSIT, time, amount), depositAccount.balance())
        return depositAccount
    }

}