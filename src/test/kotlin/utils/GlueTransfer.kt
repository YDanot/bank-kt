package utils

import domain.model.Money
import domain.model.account.AccountNumber
import domain.model.account.AccountRepository
import domain.transfer.Transfer

class GlueTransfer(private val accountRepository: AccountRepository) {

    private lateinit var amount: Money
    private lateinit var from: AccountNumber
    private lateinit var to: AccountNumber

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
        Transfer(amount, from, accountRepository).to(to)
    }

}