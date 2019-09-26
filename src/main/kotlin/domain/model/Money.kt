package domain.model

import java.util.*

data class Money(val cents: Int, val currency: Currency = Currency.getInstance("EUR")) {

    operator fun plus(money: Money): Money {
        return Money(cents + money.cents, currency)
    }

    operator fun minus(money: Money): Money {
        return Money(cents - money.cents, currency)
    }

}
