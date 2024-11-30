package net.javaguides.banking.Models;

//import lombok.Data;
//
//@Data
//public class AccountDto {
//
//    private Long id;
//    private String accountHolderName;
//    private Double balance;
//}

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccountDto(Long id,String accountHolderName,Double balance){


}
