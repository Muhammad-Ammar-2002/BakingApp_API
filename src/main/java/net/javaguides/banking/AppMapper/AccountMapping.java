package net.javaguides.banking.AppMapper;

import net.javaguides.banking.Entities.AccountEntity;
import net.javaguides.banking.Models.AccountDto;


public class AccountMapping {

    public static AccountDto toDto(AccountEntity account) {
        return new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
    }

    public static AccountEntity toEntity(AccountDto accountDto) {
        AccountEntity account = new AccountEntity();
        account.setId(accountDto.id());
        account.setAccountHolderName(accountDto.accountHolderName());
        account.setBalance(accountDto.balance());
        return account;
    }
}
