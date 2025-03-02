package dev.Zerphyis.upload.Entity.usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name = "tb_usuarios")
public class Usuarios  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String senha;
    public Usuarios(){

    }
    public  Usuarios(String nome,String email,String senha){
        this.nome=nome;
        this.email=email;
        this.senha = new BCryptPasswordEncoder().encode(senha);

    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
