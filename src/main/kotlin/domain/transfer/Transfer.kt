package domain.transfer

import domain.model.Money
import domain.model.account.Account
import domain.model.account.AccountNumber
import domain.model.account.AccountRepository
import infra.InMemoryAccountRepository

class Transfer(private val amount: Money, from: AccountNumber, private val repo: AccountRepository = InMemoryAccountRepository()) {

    private val accountFrom: Account = repo.get(from)

    fun to(accountNumber: AccountNumber) {
        val accountTo = repo.get(accountNumber)

        repo.save(accountFrom.withdraw(amount))
        repo.save(accountTo.deposit(amount))
    }

}
