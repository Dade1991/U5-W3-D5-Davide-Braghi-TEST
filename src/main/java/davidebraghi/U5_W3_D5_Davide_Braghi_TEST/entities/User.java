package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"password", "authorities", "enabled", "accountNonLocked", "accountNonExpired", "credentialsNonExpired"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String password;
    @Enumerated
    @Column(nullable = false)
    private Role role;

    public User(String username,
                String email,
                String name,
                String surname,
                String password,
                Role role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = Role.USER_TEST;
    }
}