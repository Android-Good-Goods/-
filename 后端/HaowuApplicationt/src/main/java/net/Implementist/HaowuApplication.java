package net.Implementist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class HaowuApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(HaowuApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
