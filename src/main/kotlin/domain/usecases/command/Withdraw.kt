package domain.usecases.command

import domain.model.Account
import domain.model.Money
import infra.SystemClock
import java.time.LocalDateTime

class Withdraw(
    private val amount: Money,
    private val time: LocalDateTime = SystemClock().now()
) {

    fun from(account: Account): Account {
        return account.withdraw(amount, time)
    }

}