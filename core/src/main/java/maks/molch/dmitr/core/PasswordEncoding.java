package maks.molch.dmitr.core;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoding {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var encodedPassword = bCryptPasswordEncoder.encode("pass1234");
        System.out.println(encodedPassword);
    }
}
