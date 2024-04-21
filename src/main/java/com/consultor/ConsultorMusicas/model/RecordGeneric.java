package com.consultor.ConsultorMusicas.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordGeneric(
        @JsonAlias ("artist") DadosArtista Artista,
        @JsonAlias("toptracks") DadosMusica melhoresMusicas,
        @JsonAlias ("error") Integer erro,
        @JsonAlias ("message") String mensagemErro){

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DadosMusica(
            @JsonAlias("track") List<DadosMusica> dadosMusicas,
            @JsonAlias("name") String nome,
            @JsonAlias("playcount") int reproducoes,
            @JsonAlias("artist") RecArtista artista
    ){
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record RecArtista(@JsonAlias ("name") String nome){}
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DadosArtista(@JsonAlias("name")String nome,
                               @JsonAlias("stats") RecStats reproducoes,
                               @JsonAlias ("bio") RecBio sumario){
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record RecStats(@JsonAlias("playcount")int reproducoes){}
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record RecBio(@JsonAlias("summary")String sumario){}
    }
}
