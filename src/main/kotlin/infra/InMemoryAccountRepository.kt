package infra

import domain.model.account.Account
import domain.model.account.AccountNumber
import domain.model.account.AccountRepository

class InMemoryAccountRepository(private val repo: MutableMap<AccountNumber, Account> = HashMap()) : AccountRepository {

    override fun save(account: Account): Account {
        repo.put(account.number(), account)
        return account
    }

    override fun get(accountNumber: AccountNumber): Account {
        return repo.get(accountNumber) ?: throw IllegalArgumentException("No account n° : $accountNumber")
    }
}