package domain

import org.junit.Test
import glue.*

class DepositTest {

    @Test
    fun deposit_should_be_added_to_account() {
        var account = given_an_account_with("0 EUR")
        when_I_deposit("10 EUR").on(account)
        then_balance_of(account).should_be("10 EUR")

        account = given_an_account_with("10 EUR")
        when_I_deposit("10 EUR").on(account)
        then_balance_of(account).should_be("20 EUR")
    }
}

