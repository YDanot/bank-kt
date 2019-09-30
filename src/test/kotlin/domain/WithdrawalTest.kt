package domain

import org.junit.Test
import glue.given_an_account_with
import glue.should_be
import glue.then_balance_of
import glue.when_I_withdraw

class WithdrawalTest {

    @Test
    fun withdraw_should_be_remove_from_account() {
        var account = given_an_account_with("10 EUR")
        when_I_withdraw("10 EUR").from(account)
        then_balance_of(account).should_be("0 EUR")

        account = given_an_account_with("20 EUR")
        when_I_withdraw("10 EUR").from(account)
        then_balance_of(account).should_be("10 EUR")

        account = given_an_account_with("0 EUR")
        when_I_withdraw("10 EUR").from(account)
        then_balance_of(account).should_be("-10 EUR")
    }
}

