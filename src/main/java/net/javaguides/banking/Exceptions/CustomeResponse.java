package net.javaguides.banking.Exceptions;


import java.time.LocalDateTime;

public record CustomeResponse<T>(
                            String timestamp
                           ,String message
                           ,T details
                          ,String Code
                          , Boolean success) {

}
