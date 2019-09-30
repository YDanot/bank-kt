package domain.model.account

interface AccountRepository {

    fun save(account: Account): Account
    fun get(accountNumber: AccountNumber): Account
}
