package domain.usecases.command

import domain.model.Account
import domain.model.Money
import infra.SystemClock
import java.time.LocalDateTime

class Deposit(
    private val amount: Money,
    private val time: LocalDateTime = SystemClock().now()
) {

    fun on(account: Account): Account {
        return account.deposit(amount, time)
    }

}