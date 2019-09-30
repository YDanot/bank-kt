package domain

import org.junit.Test
import utils.*

class TransferTest {

    @Test
    fun should() {
        val account1 = given_an_account_with("100 EUR")
        val account2 = given_an_other_account_with("100 EUR")

        when_I_transfer("50 EUR")
            .from(account1.number())
            .to(account2.number())

        then_balance_of(account1.number()).should_be("50 EUR")
        then_balance_of(account2.number()).should_be("150 EUR")
    }

}


