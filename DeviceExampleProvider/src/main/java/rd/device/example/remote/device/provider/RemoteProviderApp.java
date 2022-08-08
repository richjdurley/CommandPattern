package rd.device.example.remote.device.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Asynchronous command pattern implementation applied to a light device example
 */
@SpringBootApplication
@EnableAutoConfiguration
public class RemoteProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(RemoteProviderApp.class, args);
    }
}
