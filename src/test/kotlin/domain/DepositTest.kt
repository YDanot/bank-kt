package domain

import org.junit.Test
import utils.*

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
}

