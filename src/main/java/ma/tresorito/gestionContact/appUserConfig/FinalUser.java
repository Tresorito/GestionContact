package ma.tresorito.gestionContact.appUserConfig;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ma.tresorito.gestionContact.appContactConfig.finalContact;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FinalUser implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passWord;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "finalUser")
    private Set<finalContact> contactList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.getEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
