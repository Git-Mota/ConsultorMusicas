package com.consultor.ConsultorMusicas.repository;

import com.consultor.ConsultorMusicas.model.Artista;
import com.consultor.ConsultorMusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista,Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);
    @Query("SELECT varMusica FROM Artista varArtista JOIN varArtista.musicas varMusica WHERE varArtista = :artista ORDER BY varMusica.reproducoes DESC LIMIT 5")
    List<Musica> topMusicas(Artista artista);
}
