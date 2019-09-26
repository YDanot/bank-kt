package domain.model

data class Money(val cents: Int) {

    operator fun plus(money: Money): Money {
        return Money(cents + money.cents)
    }

}
