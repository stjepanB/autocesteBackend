package diplomski.autoceste;

import diplomski.autoceste.populators.BillPopulate;
import diplomski.autoceste.populators.HighwaySectionPopulate;
import diplomski.autoceste.populators.PrivateUserPopulate;
import diplomski.autoceste.populators.VehiclePopulator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class AutocesteApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutocesteApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(PrivateUserPopulate privateUserPopulate, HighwaySectionPopulate sectionPopulate,
                                 VehiclePopulator vehiclePopulator, BillPopulate billPopulate) throws Exception {
        return (String[] args) -> {
            privateUserPopulate.populate();
            sectionPopulate.populate();
            vehiclePopulator.populate();
            billPopulate.populate();
        };
    }

}
