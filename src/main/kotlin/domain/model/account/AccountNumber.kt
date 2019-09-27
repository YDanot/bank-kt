package domain.model.account

import java.util.*

data class AccountNumber(val number: String = UUID.randomUUID().toString())
