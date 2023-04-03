package ma.tresorito.gestionContact.appUserConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinalUserService implements UserDetailsService {

    private final FinalUserRepository finalUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return finalUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not exist"));
    }

    public void saveUser(FinalUser finalUser) {
        finalUserRepository.save(finalUser);
    }


}
