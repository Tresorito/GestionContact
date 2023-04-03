package ma.tresorito.gestionContact.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "gestionContact/demo")
public class demoController {

    @GetMapping("/get")
    public ResponseEntity<String> getSomething() {
        return ResponseEntity.ok("secure page access");
    }
}
