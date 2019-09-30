package utils

import domain.model.Money
import domain.model.account.Account
import domain.model.account.AccountNumber
import domain.model.account.AccountRepository
import domain.model.transaction.TransactionHistory
import domain.model.transaction.TransactionLogs
import domain.usecases.queries.GetTransactionHistory
import infra.InMemoryAccountRepository
import infra.InMemoryTransactionLogs
import org.assertj.core.api.Assertions
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

val transactionLogs: TransactionLogs = InMemoryTransactionLogs()
val accountRepository: AccountRepository = InMemoryAccountRepository()

fun then_balance_of(accountNumber: AccountNumber): Money {
    return accountRepository.get(accountNumber).balance()
}

fun then_balance_of(account: Account): Money {
    return then_balance_of(account.number())
}

fun then_history_of(account: Account): TransactionHistory {
    return GetTransactionHistory(transactionLogs).of(account.number())!!
}

fun given_an_other_account_with(s: String) = given_an_account_with(s)
fun given_an_account_with(s: String): Account {
    return accountRepository.save(Account(toMoney(s)))
}

fun given_an_account(): Account {
    return accountRepository.save(Account(toMoney("0 EUR")))
}

fun toMoney(s: String): Money {
    val split = s.split(" ")
    val cents = split[0].toBigDecimal().multiply(BigDecimal(100)).intValueExact()
    val currency = Currency.getInstance(split[1])
    return Money(cents, currency)
}

fun when_I_deposit(s: String): GlueDeposit {
    return GlueDeposit(toMoney(s), transactionLogs)
}

fun when_I_transfer(s: String): GlueTransfer {
    return GlueTransfer(accountRepository).of(toMoney(s))
}

fun given_a_deposit_of(s: String): GlueDeposit {
    return GlueDeposit(toMoney(s), transactionLogs)
}

fun given_a_withdrawal_of(s: String): GlueWithdrawal {
    return GlueWithdrawal(toMoney(s), transactionLogs)
}

fun when_I_withdraw(s: String): GlueWithdrawal {
    return GlueWithdrawal(toMoney(s), transactionLogs)
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