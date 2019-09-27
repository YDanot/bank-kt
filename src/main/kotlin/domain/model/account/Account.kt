package domain.model.account

import domain.model.Money

class Account(private val balance: Money = Money(0), private val number: AccountNumber = AccountNumber()) {

    fun balance(): Money {
        return balance
    }

    fun number(): AccountNumber {
        return number
    }

    fun deposit(money: Money): Account {
        return Account(money + balance, number)
    }

    fun withdraw(money: Money): Account {
        return Account(balance - money, number)
    }

}
