package diplomski.autoceste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AutocesteApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutocesteApplication.class, args);
    }

}
