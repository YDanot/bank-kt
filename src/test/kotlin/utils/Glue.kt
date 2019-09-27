package utils

import domain.model.Money
import domain.model.transaction.TransactionHistory
import domain.model.transaction.TransactionLogs
import domain.model.account.Account
import domain.usecases.command.Deposit
import domain.usecases.command.Withdraw
import domain.usecases.queries.GetTransactionHistory
import infra.InMemoryTransactionLogs
import org.assertj.core.api.Assertions
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

val transactionLogs: TransactionLogs = InMemoryTransactionLogs()

fun then_balance_of(account: Account): Money {
    return account.balance()
}

fun then_history_of(account: Account): TransactionHistory {
    return GetTransactionHistory(transactionLogs).of(account.number())!!
}

fun given_an_account_with(s: String): Account {
    return Account(toMoney(s))
}

fun given_an_account(): Account {
    return Account(toMoney("0 EUR"))
}

fun toMoney(s: String): Money {
    val split = s.split(" ")
    val cents = split[0].toBigDecimal().multiply(BigDecimal(100)).intValueExact()
    val currency = Currency.getInstance(split[1])
    return Money(cents, currency)
}

fun when_I_deposit(s: String): Deposit {
    return Deposit(toMoney(s))
}

fun given_a_deposit_of(s: String): GlueDeposit {
    return GlueDeposit(toMoney(s), transactionLogs)
}

fun given_a_withdrawal_of(s: String): GlueWithdrawal {
    return GlueWithdrawal(toMoney(s), transactionLogs)
}

fun when_I_withdraw(s: String): Withdraw {
    return Withdraw(toMoney(s))
}

fun Money.should_be(s: String) {
    Assertions.assertThat(this).isEqualTo(toMoney(s))
}

fun toDate(date: String): LocalDateTime {
    return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("MM/dd/uuuu HH:mm"))
}

fun clock(date: String): FixedClock {
    return FixedClock(toDate(date))
}