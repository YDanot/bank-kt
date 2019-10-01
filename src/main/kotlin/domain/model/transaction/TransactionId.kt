package domain.model.transaction

import java.util.*

data class TransactionId(val id: String = UUID.randomUUID().toString())
