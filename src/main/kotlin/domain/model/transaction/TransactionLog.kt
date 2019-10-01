package domain.model.transaction

import domain.model.Money

data class TransactionLog(
    val transaction: Transaction,
    val accountBalance: Money
)