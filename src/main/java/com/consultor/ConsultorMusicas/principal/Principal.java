package com.consultor.ConsultorMusicas.principal;

import com.consultor.ConsultorMusicas.model.Artista;
import com.consultor.ConsultorMusicas.model.Musica;
import com.consultor.ConsultorMusicas.model.RecordGeneric;
import com.consultor.ConsultorMusicas.repository.ArtistaRepository;
import com.consultor.ConsultorMusicas.services.ConsumoApi;
import com.consultor.ConsultorMusicas.services.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://ws.audioscrobbler.com/2.0/";
    private final String API_KEY = "&api_key=" + System.getenv("LASTFM_APIKEY");
    private ArtistaRepository repositorio;
    private List<Artista> artistas = new ArrayList<>();
    private  List<Musica> musicas = new ArrayList<>();

    public Principal (ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibirMenu(){
        try {
            var opcao = -1;
            while (opcao !=0) {
                var menu = """
                    \n
                    1 - Buscar artistas.
                    2 - Cadastrar Musicas.
                    3 - Listar Musicas.
                    4 - Buscar Musicar por Artistas.
                    5 - Pesquisar dados sobre um artista.
                    
                    
                    0 - Sair;
                    
                    Digite a opção desejada: 
                    """;

                System.out.println(menu);
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao){
                    case 1:
                        cadastraArtista();
                        break;
                    case 2:
                        cadastrarMusica();
                        break;
                    case 3:
                        listarTodasMusicas();
                        break;
                    case 4:
                        listarMusicasPorArtista();
                        break;
                    case 5:
                        listarInformacoesArtista();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção invalida");
                        break;
                }
            }
        }catch (Exception e){
            System.out.println("Erro:" + e);
        }
    }
    private Optional<Artista> buscarTodasInformacoesArtista(String nomeArtista) {
        return repositorio.findByNomeContainingIgnoreCase(nomeArtista);
    }
    private void cadastraArtista() {
        try {
            System.out.println("Digite o nome do artista: ");
            var nomeArtista = leitura.nextLine();
            System.out.println("Digite a categoria do artista:Ex.: Solo, Dupla ou banda  ");
            var categoria = leitura.nextLine().toUpperCase();
            var json = consumo.obterDados(ENDERECO + "?method=artist.getinfo&artist="+ nomeArtista.replace(" ", "+") + API_KEY + "&format=json");
            RecordGeneric dadosArtista = conversor.obterDados(json, RecordGeneric.class);

            if (dadosArtista.mensagemErro() == null) {
                Artista artista = new Artista(dadosArtista, categoria);
                System.out.println(artista);
                repositorio.save(artista);
            } else {
                System.out.println(dadosArtista.mensagemErro());
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void cadastrarMusica(){
        try{
            listarArtistasBuscados();
            System.out.println("Escolha um artista pelo nome");
            var nomeArtista = leitura.nextLine();

            var artista = buscarTodasInformacoesArtista(nomeArtista);
            if (artista.isPresent()){
                var artistaEncontrado = buscarTodasInformacoesArtista(nomeArtista).get();
                var json = consumo.obterDados(ENDERECO + "?method=artist.gettoptracks&artist="+ artistaEncontrado.getNome().replace(" ", "+") + API_KEY + "&format=json");
                RecordGeneric melhoresMusicas = conversor.obterDados(json,RecordGeneric.class);
                List<RecordGeneric.DadosMusica> musicas = new ArrayList<>(melhoresMusicas.melhoresMusicas().dadosMusicas());

                List<Musica> musicasList = musicas.stream()
                        .map( m -> new Musica(m))
                        .collect(Collectors.toList());
                musicasList.forEach(System.out::println);
                artistaEncontrado.setMusicas(musicasList);
                repositorio.save(artistaEncontrado);
                System.out.println("Musicas salvas com sucesso !");
            }else{
                System.out.println("Artista não econtrado");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void listarArtistasBuscados(){
        try {
            artistas = repositorio.findAll();
            artistas.stream()
                    .sorted(Comparator.comparing(Artista::getCategoria))
                    .forEach(System.out::println);
        }catch (Exception e){
            System.out.println("Erro: " + e );
        }
    }
    private void listarTodasMusicas() {
    try {
        artistas = repositorio.findAll();
        artistas.forEach(a -> System.out.println("\nArtista: " + a.getNome() + "\n" + a.getMusicas()));
    }catch (Exception e){
        System.out.println("Erro: " + e);
    }
    }
    private void listarMusicasPorArtista(){
        try{
        System.out.println("Digite o nome do artista");
        var nomeArtista = leitura.nextLine();
        List<Musica> musicaList = buscarTodasInformacoesArtista(nomeArtista).get().getMusicas();
        System.out.println(musicaList);
        }catch (Exception e){
            System.out.println("Erro: "+e);
        }
    }
    private void listarInformacoesArtista() {
        try {
            System.out.println("Digite o nome do artista: ");
            var nomeArtista = leitura.nextLine();
            var dadosArtista = buscarTodasInformacoesArtista(nomeArtista);
            if (dadosArtista.isPresent()) {
                var artista = dadosArtista.get();
                System.out.println(artista);
                List<Musica> melhoresMusicas = repositorio.topMusicas(artista);
                System.out.println("\nTOP 05 Melhores músicas: \n" + melhoresMusicas);
            } else {
                System.out.println("Artista não econtrado");
            }
        }catch (Exception e){
            System.out.println("Erro: " + e);
        }
    }
}