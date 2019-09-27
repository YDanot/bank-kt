package domain.usecases.queries

import domain.model.Account
import domain.model.History

class GetTransactionHistory() {

    fun of(account: Account): History {
        return account.history()
    }

}