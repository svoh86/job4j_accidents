package ru.job4j.accidents;

import net.jcip.annotations.ThreadSafe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("secret");
        System.out.println(password);

        SpringApplication.run(Main.class, args);
        System.out.println("Go to http://localhost:8080/index");
    }
}
