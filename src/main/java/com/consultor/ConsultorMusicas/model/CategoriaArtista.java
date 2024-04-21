package com.consultor.ConsultorMusicas.model;

public enum CategoriaArtista {
    SOLO("SOLO"),
    DUPLA("DUPLA"),
    BANDA("BANDA");

    private String categoriaApiLastFm;

    CategoriaArtista(String categoriaApiDeezer){
        
        this.categoriaApiLastFm = categoriaApiDeezer;
    }
    public static CategoriaArtista fromString(String text){
        for(CategoriaArtista categoria : CategoriaArtista.values()){
            if (categoria.categoriaApiLastFm.equalsIgnoreCase(text)){
                return  categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria econtrada");
    }
}
