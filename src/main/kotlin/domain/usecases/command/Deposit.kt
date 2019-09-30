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

class Deposit(
    private val amount: Money,
    private val time: LocalDateTime = SystemClock().now(),
    private val transactionLogs: TransactionLogs = InMemoryTransactionLogs(),
    private val accountRepository: AccountRepository = InMemoryAccountRepository()
) {

    fun on(accountNumber: AccountNumber): Account {
        val account = accountRepository.get(accountNumber)
        val depositAccount = accountRepository.save(account.deposit(amount))
        transactionLogs.log(
            accountNumber,
            Transaction(TransactionType.DEPOSIT, time, amount), depositAccount.balance()
        )
        return depositAccount
    }

}