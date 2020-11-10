package lightapp.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DEMO COMMAND PATTERN USING LIGHT ON AND OFF COMMANDS TO ASYNC CALLABLE API, NOTE AS THESE
 * COMMANDS CAN BE SENT CONCURRENTLY THEY CAN FAIL IF THE SECOND COMMAND GETS EXECUTED BEFORE THE
 * FIRST COMMAND!
 */
@SpringBootApplication
@EnableAutoConfiguration
public class LightApp {
    public static void main(String[] args) {
        SpringApplication.run(LightApp.class, args);
    }
}
