package domain.usecases

import domain.model.Account
import domain.model.Money

class Deposit(val amount: Money) {

    fun on(account: Account): Account {
        return account.deposit(amount)
    }

}