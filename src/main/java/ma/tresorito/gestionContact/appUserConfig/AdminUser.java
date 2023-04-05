package ma.tresorito.gestionContact.appUserConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class AdminUser {

    @Bean
    CommandLineRunner commandLineRunner(FinalUserRepository finalUserRepository,
                                        PasswordEncoder passwordEncoder){

        return args -> {
            FinalUser admin = FinalUser.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .passWord(passwordEncoder.encode("Admin@2023"))
                    .email("Admin@gmail.com")
                    .role(Role.ADMIN)
                    .contactList(new HashSet<>())
                    .build();

            finalUserRepository.save(admin);
        };
    }
}
