package ma.tresorito.gestionContact.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "gestionContact/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));

    }

    @PostMapping("/signUp")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

}
