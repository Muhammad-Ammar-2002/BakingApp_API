package net.javaguides.banking.Services;

import net.javaguides.banking.Models.AccountDto;

import java.io.Serializable;
import java.util.List;

public interface AccountServiceInt {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);


    AccountDto deposit(Long id, Double amount);

    AccountDto withdraw(Long id, Double amount);

    List<AccountDto> getAllAccounts();

    String deleteAccount(Long id);
}
