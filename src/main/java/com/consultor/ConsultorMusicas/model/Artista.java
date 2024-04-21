package com.consultor.ConsultorMusicas.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private CategoriaArtista categoria;

    private int reproducoes;
    @Column(length = 1000)
    private String bibliografia;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas =new ArrayList<>();

    public Artista(){}
    public Artista(RecordGeneric dadosArtista, String categoria){
        this.nome = dadosArtista.Artista().nome();
        this.reproducoes = dadosArtista.Artista().reproducoes().reproducoes();
        this.bibliografia = dadosArtista.Artista().sumario().sumario();
        this.categoria = CategoriaArtista.fromString(categoria.split(",")[0].trim());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    public void setReproducoes(int reproducoes) {
        this.reproducoes = reproducoes;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }
    public CategoriaArtista getCategoria() {

        return categoria;
    }

    public void setCategoria(CategoriaArtista categoria) {
        this.categoria = categoria;
    }
    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        musicas.forEach(m -> m.setArtista(this));
        this.musicas = musicas;
    }


    @Override
    public String toString() {
        return  "\nNome:'" + nome + '\'' +
                "\nCategoria: " + categoria +
                "\nReproucoes:" + reproducoes +
                "\nBibliografia:'" + bibliografia + '\'';
    }
}


