package infra.rendering

import domain.model.account.Account
import glue.*
import infra.ConsoleRenderer
import org.assertj.core.api.Assertions
import org.junit.Test

class StatementPrintingTest {

    @Test
    fun statement() {
        val account = given_an_account()
        given_a_deposit_of("1000 EUR").has_been_done_on(account).the("01/10/2012 08:00")
        given_a_deposit_of("2000 EUR").has_been_done_on(account).the("01/13/2012 08:00")
        given_a_withdrawal_of("500 EUR").has_been_done_on(account).the("01/14/2012 08:00")
        then_statement_history_of(account).should_be_printed(
            "date || credit || debit || balance",
            "14/01/2012 08:00 || || 500,00 € || 2 500,00 €",
            "13/01/2012 08:00 || 2 000,00 € || || 3 000,00 €",
            "10/01/2012 08:00 || 1 000,00 € || || 1 000,00 €"
        )
    }

    @Test
    fun statement_with_transfers() {
        val account1 = given_an_account()
        given_a_deposit_of("1000 EUR").has_been_done_on(account1).the("01/10/2012 08:00")
        val account2 = given_an_other_account()
        given_a_transfer_of("500 EUR").the("01/14/2012 08:00").from(account1.number()).to(account2.number())

        then_statement_history_of(account1).should_be_printed(
            "date || credit || debit || balance",
            "14/01/2012 08:00 || || 500,00 € || 500,00 €",
            "10/01/2012 08:00 || 1 000,00 € || || 1 000,00 €"
        )

        then_statement_history_of(account2).should_be_printed(
            "date || credit || debit || balance",
            "14/01/2012 08:00 || 500,00 € || || 500,00 €"
        )
    }

    private fun then_statement_history_of(account: Account): String {
        return ConsoleRenderer().statement(transactionLogs.logsOf(account.number())!!)
    }

    private fun String.should_be_printed(vararg s: String) {
        Assertions.assertThat(s.map { it.replace('\u00A0', ' ') })
            .containsExactlyElementsOf(this.replace('\u00A0', ' ').split("\n"))
    }

}


