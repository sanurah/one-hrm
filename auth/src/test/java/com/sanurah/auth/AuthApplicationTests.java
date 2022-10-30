package com.sanurah.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AuthApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void ecodeTrar() {
        String u = new BCryptPasswordEncoder().encode("0neHrmUser!");
        System.out.println(u);
    }

}
