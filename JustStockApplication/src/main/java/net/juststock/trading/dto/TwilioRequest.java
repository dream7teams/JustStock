package net.juststock.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwilioRequest {
    private String toPhoneNumber;
    private String fromPhoneNumber;
    private String message;
}

