package net.javaguides.banking.Controllers;

import lombok.AllArgsConstructor;
import net.javaguides.banking.Exceptions.CustomeResponse;
import net.javaguides.banking.Models.AccountDto;
import net.javaguides.banking.Services.AccountServiceInt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountServiceInt accountServiceInt;


    @PostMapping("/add_account")
    private ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountServiceInt.createAccount(accountDto), HttpStatus.CREATED);

    }


    @GetMapping("/find_account/{id}")
    private ResponseEntity<?> findAccount(@PathVariable Long id)
    {
        return new ResponseEntity<>(accountServiceInt.getAccountById(id), HttpStatus.OK);

    }


    @PutMapping("/deposit")
    private ResponseEntity<?> deposit(@RequestParam Long id, @RequestBody Map<String,Double>request)
    {
        return new ResponseEntity<>(accountServiceInt.deposit(id,request.get("amount")), HttpStatus.OK);

    }

    @PatchMapping("/withdraw")
    private ResponseEntity<?> withdraw(@RequestParam Long id, @RequestBody Map<String,Double>request)
    {
        return new ResponseEntity<>(
                new CustomeResponse<>(
                        LocalDateTime.now().toString(),
                        "Account Exist ",
                        accountServiceInt.withdraw(id,request.get("amount")),
                                "CONFIRMED_TRANSACTION",
                                Boolean.TRUE
                )
        , HttpStatus.OK);

    }


    @GetMapping("/all_accounts")
    private ResponseEntity<?> getAllAccounts()
    {
            return new ResponseEntity<>(accountServiceInt.getAllAccounts(), HttpStatus.OK);
    }

    @DeleteMapping ("/delete_account")
    private ResponseEntity<?> deleteAccount(@RequestParam Long id) {
        return new ResponseEntity<>(accountServiceInt.deleteAccount(id), HttpStatus.OK);
    }



}
