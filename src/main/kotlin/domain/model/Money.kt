package domain.model

import java.text.NumberFormat
import java.util.*

data class Money(val cents: Int = 0, val currency: Currency = Currency.getInstance("EUR")) {

    operator fun plus(money: Money): Money {
        return Money(cents + money.cents, currency)
    }

    operator fun minus(money: Money): Money {
        return Money(cents - money.cents, currency)
    }

    override fun toString(): String {
        return NumberFormat.getCurrencyInstance().format(cents.toDouble() / 100)
    }

}
