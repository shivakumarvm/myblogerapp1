package com.myblog1.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {
    public static void main(String[] arrgs){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("testing"));
    }
}
