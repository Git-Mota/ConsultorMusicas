package com.consultor.ConsultorMusicas.model;

import jakarta.persistence.*;

@Entity
@Table (name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int reproducoes;
    @Transient
    private String artistaNome;
    @ManyToOne
    private Artista artista;

    public Musica(RecordGeneric.DadosMusica dadosMusica){
        this.nome = dadosMusica.nome();
        this.reproducoes =dadosMusica.reproducoes();
        this.artistaNome = dadosMusica.artista().nome();
    }
    public Musica (){}


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

    public String getArtistaNome() {
        return artistaNome;
    }

    public void setArtistaNome(String artistaNome) {
        this.artistaNome = artistaNome;
    }
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return  "\n\nMusica:" +
                "\nNome='" + nome + '\'' +
                "\nReporducoes=" + reproducoes;
    }
}
