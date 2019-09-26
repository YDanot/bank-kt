package utils

import domain.model.Account
import domain.model.Money
import org.assertj.core.api.Assertions
import java.math.BigDecimal
import java.util.*

fun then_balance_of(account: Account): Money {
    return account.balance()
}

fun given_an_account_with(s: String): Account {
    return Account(toMoney(s))
}

fun toMoney(s: String): Money {
    val split = s.split(" ")
    val cents = split.get(0).toBigDecimal().multiply(BigDecimal(100)).intValueExact()
    val currency = Currency.getInstance(split.get(1))
    return Money(cents,currency)
}


fun when_I_deposit(s: String): Money {
    return toMoney(s)
}

fun Money.on(account: Account): Account {
    return account.deposit(this)
}

fun Money.should_be(s: String) {
    Assertions.assertThat(this).isEqualTo(toMoney(s))
}