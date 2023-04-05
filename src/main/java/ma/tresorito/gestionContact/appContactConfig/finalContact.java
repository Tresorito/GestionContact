package ma.tresorito.gestionContact.appContactConfig;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tresorito.gestionContact.appUserConfig.FinalUser;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private FinalUser finalUser;

    public finalContact(String name, String position, String email, int project) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.project = project;
    }
}
