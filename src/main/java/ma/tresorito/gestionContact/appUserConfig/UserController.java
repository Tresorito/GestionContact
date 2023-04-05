package ma.tresorito.gestionContact.appUserConfig;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "gestionContact/v1")
public class UserController {

    @GetMapping("user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> userPage() {
        return ResponseEntity.ok("Welcome to User Page");
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("Welcome to Admin Page");
    }

}
