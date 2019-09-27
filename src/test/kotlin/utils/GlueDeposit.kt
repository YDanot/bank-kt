package utils

import domain.model.Account
import domain.model.Money
import domain.usecases.command.Deposit

class GlueDeposit(private val amount: Money) {

    private lateinit var account: Account

    fun has_been_done_on(account: Account): GlueDeposit {
        this.account = account
        return this
    }

    fun the(date: String): Account {
        val clock = clock(date)
        return Deposit(amount, clock.now()).on(account)
    }

}
