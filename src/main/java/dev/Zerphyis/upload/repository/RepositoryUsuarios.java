package dev.Zerphyis.upload.repository;

import dev.Zerphyis.upload.Entity.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUsuarios extends JpaRepository<Usuarios,Long> {
    Optional<Usuarios> findByEmail(String email);
}
