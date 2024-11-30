package net.javaguides.banking.Services.Impl;

import lombok.AllArgsConstructor;
import net.javaguides.banking.AppMapper.AccountMapping;
import net.javaguides.banking.Entities.AccountEntity;
import net.javaguides.banking.Exceptions.AccountException;
import net.javaguides.banking.Exceptions.IllegalArgumentException;
import net.javaguides.banking.Exceptions.ServiceException;
import net.javaguides.banking.Models.AccountDto;
import net.javaguides.banking.Repos.AccountRepo;
import net.javaguides.banking.Services.AccountServiceInt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;
import java.util.stream.Collectors;

import static net.javaguides.banking.AppMapper.AccountMapping.toDto;
import static net.javaguides.banking.AppMapper.AccountMapping.toEntity;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountServiceInt {

    private final AccountRepo accountRepo;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        if (accountDto != null) {
            return toDto(accountRepo.save(toEntity(accountDto)));

        }

        return null;
    }

    @Override
    public AccountDto getAccountById(Long id) {

        return toDto(
                accountRepo.findById(id).orElseThrow(() -> new AccountException("Account not found.")));


    }

    @Override
    public AccountDto deposit(Long id, Double amount) {

        try {
            AccountEntity account = toEntity(getAccountById(id));

            if (!(amount > 0)) {
                throw new IllegalArgumentException("Amount to deposit must be greater than 0.");
            }
            account.setBalance(account.getBalance() + amount);
            return mapper.map(accountRepo.save(account), AccountDto.class);


        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while depositing funds.");
        }
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {

        AccountEntity account = toEntity(getAccountById(id));

        if (!(amount > 0)) {
            throw new IllegalArgumentException("Amount to withdraw must be greater than 0.");
        }
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient amount");
        }

        account.setBalance(account.getBalance() - amount);
        return toDto(accountRepo.save(account));


    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepo.findAll()
                .stream()
                .map(AccountMapping::toDto).collect(Collectors.toList());
    }

    @Override
    public String deleteAccount(Long id) {

        if (getAccountById(id) != null) {
            Double Remaining = getAccountById(id).balance();
            accountRepo.deleteById(id);
            return "your account has been deleted.\n This is the your Remaining funds : " + Remaining;
        }
       throw new AccountException("Failed to confirm deletion process");

    }



}