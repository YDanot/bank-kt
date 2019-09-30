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

class Withdraw(
    private val amount: Money,
    private val time: LocalDateTime = SystemClock().now(),
    private val transactionLog: TransactionLogs = InMemoryTransactionLogs(),
    private val accountRepository: AccountRepository = InMemoryAccountRepository()
) {

    fun from(accountNumber: AccountNumber): Account {
        val account = accountRepository.get(accountNumber)
        val withdrawAccount = account.withdraw(amount)
        transactionLog.log(
            accountNumber,
            Transaction(TransactionType.WITHDRAWAL, time, amount), withdrawAccount.balance()
        )
        return accountRepository.save(withdrawAccount)
    }

}