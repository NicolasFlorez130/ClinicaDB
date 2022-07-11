package company.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Dataloader implements ApplicationRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        appUserRepository.save(new AppUser("Admin", "Admin", "admin@gmail.com", passwordEncoder.encode("pass"), AppUserRoles.ADMIN));
        appUserRepository.save(new AppUser("User", "User", "user@gmail.com", passwordEncoder.encode("pass"), AppUserRoles.USER));
    }
}
