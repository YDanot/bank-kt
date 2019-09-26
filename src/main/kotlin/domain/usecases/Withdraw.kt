package domain.usecases

import domain.model.Account
import domain.model.Money

class Withdraw(val amount: Money) {

    fun from(account: Account): Account {
        return account.withdraw(amount)
    }

}