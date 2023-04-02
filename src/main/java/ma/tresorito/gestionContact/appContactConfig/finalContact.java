package ma.tresorito.gestionContact.appContactConfig;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class finalContact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String position;
    private String email;
    private int project;

    public finalContact(String name, String position, String email, int project) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.project = project;
    }
}
