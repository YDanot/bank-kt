package domain.usecases.command

import domain.model.Money
import domain.model.account.Account
import domain.model.account.AccountNumber
import domain.model.account.AccountRepository
import domain.model.transaction.Transaction
import domain.model.transaction.TransactionLogs
import domain.model.transaction.TransactionType
import infra.InMemoryAccountRepository
import infra.InMemoryTransactionLogs
import infra.SystemClock
import java.time.LocalDateTime

class Transfer(
    private val amount: Money,
    from: AccountNumber,
    private val time: LocalDateTime = SystemClock().now(),
    private val repo: AccountRepository = InMemoryAccountRepository(),
    private val transactionLogs: TransactionLogs = InMemoryTransactionLogs()
) {

    private val accountFrom: Account = repo.get(from)

    fun to(accountNumber: AccountNumber) {
        val accountTo = repo.get(accountNumber)
        val from = repo.save(accountFrom.withdraw(amount))
        val to = repo.save(accountTo.deposit(amount))
        transactionLogs.log(accountNumber,
            Transaction(TransactionType.INWARD_TRANSFER, time, amount), to.balance())
        transactionLogs.log(from.number(),
            Transaction(TransactionType.OUTGOING_TRANSFER, time, amount), from.balance())
    }

}
