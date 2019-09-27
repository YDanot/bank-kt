package domain.usecases

import domain.model.Account
import domain.model.Clock
import domain.model.Money
import infra.SystemClock

class Deposit(private val amount: Money, private val clock:Clock = SystemClock()) {

    fun on(account: Account): Account {
        return account.deposit(amount, clock.now())
    }

}