package utils

import domain.model.Money
import domain.model.transaction.TransactionLogs
import domain.model.account.Account
import domain.usecases.command.Withdraw

class GlueWithdrawal(private val amount: Money, val transactionLogs: TransactionLogs) {

    private lateinit var account: Account

    fun has_been_done_on(account: Account): GlueWithdrawal {
        this.account = account
        return this
    }

    fun the(date: String): Account {
        val clock = clock(date)
        return Withdraw(amount, clock.now(), transactionLogs).from(account)
    }

}
