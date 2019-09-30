package utils

import domain.model.Money
import domain.model.transaction.TransactionLogs
import domain.model.account.Account
import domain.usecases.command.Deposit
import domain.usecases.command.Withdraw

class GlueWithdrawal(private val amount: Money, private val transactionLogs: TransactionLogs) {

    private lateinit var account: Account
    private var clock = clock("09/30/2019 10:00")

    fun has_been_done_on(account: Account): GlueWithdrawal {
        this.account = account
        return this
    }

    fun the(date: String): Account {
        clock = clock(date)
        return from(account)
    }

    fun from(account: Account): Account {
        return Withdraw(amount, clock.now(), transactionLogs, accountRepository).from(account)
    }
}
