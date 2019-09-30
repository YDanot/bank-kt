package glue

import domain.model.Money
import domain.model.account.Account
import domain.model.account.AccountRepository
import domain.model.transaction.TransactionLogs
import domain.usecases.command.Deposit

class GlueDeposit(
    private val amount: Money,
    private val transactionLogs: TransactionLogs,
    private val accountRepository: AccountRepository
) {

    private lateinit var account: Account
    private var clock = clock("09/30/2019 10:00")

    fun has_been_done_on(account: Account): GlueDeposit {
        this.account = account
        return this
    }

    fun the(date: String): Account {
        clock = clock(date)
        return on(account)
    }

    fun on(account: Account): Account {
        return Deposit(amount, clock.now(), transactionLogs, accountRepository).on(account.number())
    }

}
