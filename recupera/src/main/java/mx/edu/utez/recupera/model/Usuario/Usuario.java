package mx.edu.utez.recupera.model.Usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 40)
    private String username;
    @Column(nullable = false,length = 150)
    private String password;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status;

}
