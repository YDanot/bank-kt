package infra.rendering

import domain.model.Account
import infra.ConsoleRenderer
import org.assertj.core.api.Assertions
import org.junit.Test
import utils.given_a_deposit_of
import utils.given_a_withdrawal_of
import utils.given_an_account

class ConsoleRendererTest {

    @Test
    fun statement() {
        var account = given_an_account()
        account = given_a_deposit_of("1000 EUR").has_been_done_on(account).the("01/10/2012 08:00")
        account = given_a_deposit_of("2000 EUR").has_been_done_on(account).the("01/13/2012 08:00")
        account = given_a_withdrawal_of("500 EUR").has_been_done_on(account).the("01/14/2012 08:00")
        then_statement_history_of(account).should_be_printed(
            "date || credit || debit || balance" + "\n" +
                    "14/01/2012 08:00 || || 500,00 € || 2 500,00 €" + "\n" +
                    "13/01/2012 08:00 || 2 000,00 € || || 3 000,00 €" + "\n" +
                    "10/01/2012 08:00 || 1 000,00 € || || 1 000,00 €"
        )
    }

    private fun then_statement_history_of(account: Account): String {
        return ConsoleRenderer().statement(account.history())
    }

    private fun String.should_be_printed(s: String) {
        Assertions.assertThat(this.replace('\u00A0', ' ')).isEqualTo(s.replace('\u00A0', ' '))
    }

}


