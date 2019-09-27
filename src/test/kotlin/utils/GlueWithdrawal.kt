package utils

import domain.model.Account
import domain.model.Money
import domain.usecases.command.Withdraw

class GlueWithdrawal(private val amount: Money) {

    private lateinit var account: Account

    fun has_been_done_on(account: Account): GlueWithdrawal {
        this.account = account
        return this
    }

    fun the(date: String): Account {
        val clock = clock(date)
        return Withdraw(amount, clock.now()).from(account)
    }

}
