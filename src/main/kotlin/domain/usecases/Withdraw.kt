package domain.usecases

import domain.model.Account
import domain.model.Clock
import domain.model.Money
import infra.SystemClock

class Withdraw(private val amount: Money, private val clock: Clock = SystemClock()) {

    fun from(account: Account): Account {
        return account.withdraw(amount, clock.now())
    }

}