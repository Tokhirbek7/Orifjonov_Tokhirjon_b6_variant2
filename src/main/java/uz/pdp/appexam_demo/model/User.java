package uz.pdp.appexam_demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String LastName;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false, unique = true)
    String password;
    @ManyToMany()
    @JoinTable(
            name = "users_roles",
            joinColumns =  @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id")
    )
    List<Role> roles;
}
