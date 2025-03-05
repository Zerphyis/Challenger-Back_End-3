package dev.zerphyis.upload.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.zerphyis.upload.model.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByEmail(String email);
}
