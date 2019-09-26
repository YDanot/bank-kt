package domain

import domain.model.Account
import domain.model.Money
import org.assertj.core.api.Assertions
import org.junit.Test
import java.math.BigDecimal
import java.util.*

class DepositTest {

    @Test
    fun should_deposit_10_euros_on_an_empty_account() {
        var account = given_an_account_with("0 EUR")
        account = when_I_deposit("10 EUR").on(account)
        then_balance_of(account).should_be("10 EUR")
    }

    @Test
    fun should_deposit_10_euros_on_account_with_balance_10() {
        var account = given_an_account_with("10 EUR")
        account = when_I_deposit("10 EUR").on(account)
        then_balance_of(account).should_be("20 EUR")
    }

    private fun then_balance_of(account: Account): Money {
        return account.balance()
    }

    private fun given_an_account_with(s: String): Account {
        return Account(toMoney(s))
    }

    private fun toMoney(s: String): Money {
        val split = s.split(" ")
        val cents = split.get(0).toBigDecimal().multiply(BigDecimal(100)).intValueExact()
        val currency = Currency.getInstance(split.get(1))
        return Money(cents,currency)
    }


    private fun when_I_deposit(s: String): Money {
        return toMoney(s)
    }

    private fun Money.on(account: Account): Account {
        return account.deposit(this)
    }

    private fun Money.should_be(s: String) {
        Assertions.assertThat(this).isEqualTo(toMoney(s))
    }
}

