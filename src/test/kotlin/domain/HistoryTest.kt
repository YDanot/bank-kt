package domain

import domain.model.transaction.Transaction
import domain.model.transaction.TransactionHistory
import domain.model.transaction.TransactionType
import glue.*
import org.assertj.core.api.Assertions
import org.junit.Test

class HistoryTest {

    @Test
    fun deposit_should_be_added_to_history() {
        val account = given_an_account()
        given_a_deposit_of("100 EUR").has_been_done_on(account).the("04/04/2019 08:00")
        given_a_deposit_of("110 EUR").has_been_done_on(account).the("04/05/2019 08:00")
        then_history_of(account).should_contains(
            "+ 100 EUR the 04/04/2019 08:00",
            "+ 110 EUR the 04/05/2019 08:00"
        )
    }

    @Test
    fun withdrawal_should_be_added_to_history() {
        val account = given_an_account_with("250 EUR")
        given_a_withdrawal_of("100 EUR").has_been_done_on(account).the("04/04/2019 08:00")
        given_a_withdrawal_of("110 EUR").has_been_done_on(account).the("04/05/2019 08:00")
        then_history_of(account).should_contains(
            "- 100 EUR the 04/04/2019 08:00",
            "- 110 EUR the 04/05/2019 08:00"
        )
    }

    @Test
    fun transfer_should_be_added_to_history() {
        val account1 = given_an_account_with("250 EUR")
        val account2 = given_an_account_with("100 EUR")
        given_a_transfer_of("10 EUR").the("04/04/2019 08:00").from(account2.number()).to(account1.number())
        given_a_transfer_of("10 EUR").the("08/04/2019 08:00").from(account1.number()).to(account2.number())

        then_history_of(account1).should_contains(
            "> 10 EUR the 04/04/2019 08:00",
            "< 10 EUR the 08/04/2019 08:00"
        )
        then_history_of(account2).should_contains(
            "< 10 EUR the 04/04/2019 08:00",
            "> 10 EUR the 08/04/2019 08:00"
        )
    }
}

private fun TransactionHistory.should_contains(vararg s: String) {
    val actual = this.map { it.transaction }
    val expected = s.map { toTransaction(it) }
    Assertions.assertThat(expected).containsAll(actual)
    Assertions.assertThat(actual).containsAll(expected)
}

fun toTransaction(s: String): Transaction {
    val groups: MatchGroupCollection = "^([+|-|<|>]) (\\d+ .*) the (.*)".toRegex().find(s)!!.groups

    return Transaction(
        type(groups[1]!!.value),
        toDate(groups[3]!!.value),
        toMoney(groups[2]!!.value)
    )
}

fun type(matchGroup: String): TransactionType {
    return when (matchGroup) {
        "+" -> TransactionType.DEPOSIT
        ">" -> TransactionType.INWARD_TRANSFER
        "<" -> TransactionType.OUTGOING_TRANSFER
        "-" -> TransactionType.WITHDRAWAL
        else -> throw IllegalArgumentException()
    }
}
