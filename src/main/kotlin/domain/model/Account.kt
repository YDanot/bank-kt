package domain.model

class Account(private val balance: Money = Money(0)) {

    fun deposit(money: Money): Account {
        return Account(money + balance)
    }

    fun balance(): Money {
        return balance
    }

    fun withdraw(money: Money): Account {
        return Account(balance - money)
    }

}
