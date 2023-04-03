package ma.tresorito.gestionContact.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SignInRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String passWord;
}
