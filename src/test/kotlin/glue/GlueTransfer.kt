package glue

import domain.model.Money
import domain.model.account.AccountNumber
import domain.model.account.AccountRepository
import domain.model.transaction.TransactionLogs
import domain.usecases.command.Transfer

class GlueTransfer(private val accountRepository: AccountRepository, private val transactionLogs: TransactionLogs) {

    private lateinit var amount: Money
    private lateinit var from: AccountNumber
    private lateinit var to: AccountNumber
    private var clock = clock("09/30/2019 10:00")

    fun of(amount: Money): GlueTransfer {
        this.amount = amount
        return this
    }

    fun from(account: AccountNumber): GlueTransfer {
        this.from = account
        return this
    }

    fun to(account: AccountNumber) {
        this.to = account
        Transfer(amount, from, clock.now(), accountRepository, transactionLogs).to(to)
    }

    fun the(date: String): GlueTransfer {
        clock = clock(date)
        return this
    }

}