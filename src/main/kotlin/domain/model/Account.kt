package domain.model

class Account(private val balance: Money = Money(0)) {

    fun deposit(euros: Money): Account {
        return Account(euros + balance)
    }

    fun balance(): Money {
        return balance
    }

}