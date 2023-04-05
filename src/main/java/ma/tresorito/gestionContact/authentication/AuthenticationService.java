package ma.tresorito.gestionContact.authentication;

import lombok.RequiredArgsConstructor;
import ma.tresorito.gestionContact.appUserConfig.FinalUser;
import ma.tresorito.gestionContact.appUserConfig.FinalUserService;
import ma.tresorito.gestionContact.appUserConfig.Role;
import ma.tresorito.gestionContact.jwtSecurity.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final FinalUserService finalUserService;

    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        FinalUser user = FinalUser.builder()
                .firstName(signInRequest.getFirstName())
                .lastName(signInRequest.getLastName())
                .email(signInRequest.getEmail())
                .passWord(passwordEncoder.encode(signInRequest.getPassWord()))
                .role(Role.USER)
                .contactList(new HashSet<>())
                .build();

        finalUserService.saveUser(user);


        String token = jwtService.generateToken(Map.of("Authentication", user.getAuthorities()),user);
        return AuthenticationResponse.builder()
                               .token(token)
                               .build();


    }

    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
               signUpRequest.getEmail(),
               signUpRequest.getPassWord()
        );
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        }catch (DisabledException d) {
            throw new IllegalStateException("user disabled");
        }catch (BadCredentialsException b) {
            throw new IllegalStateException("Bad credential(s) from user");
        }

        UserDetails user = userDetailsService.loadUserByUsername(signUpRequest.getEmail());

        String token = jwtService.generateToken(Map.of("Authentication", user.getAuthorities()), user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
