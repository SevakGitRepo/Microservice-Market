package com.sevak.security.restcontroller.logpass;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
