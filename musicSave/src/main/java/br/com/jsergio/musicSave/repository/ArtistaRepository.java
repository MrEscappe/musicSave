package br.com.jsergio.musicSave.repository;

import br.com.jsergio.musicSave.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);
}
