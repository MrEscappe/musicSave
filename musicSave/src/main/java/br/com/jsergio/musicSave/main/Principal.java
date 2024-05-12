package br.com.jsergio.musicSave.main;

import br.com.jsergio.musicSave.model.Artista;
import br.com.jsergio.musicSave.model.Musica;
import br.com.jsergio.musicSave.model.TipoArtista;
import br.com.jsergio.musicSave.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository artistaRepository;
    Scanner scanner = new Scanner(System.in);

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {

            var menu = """
                    --> MusicSave <--
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artista
                                    
                    0- Sair
                    """;


            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }


        }
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Informe o nome do artista: ");
        var nomeArtista = scanner.nextLine();
        Optional<Artista> artistaOptional = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if(artistaOptional.isEmpty()) {
            System.out.println("Artista não encontrado!");
            return;
        } else {
            System.out.println("Músicas do artista: " + artistaOptional.get().getNome());
            artistaOptional.get().getMusicas().forEach(musica -> {
                System.out.println("Música: " + musica.getTitulo());
            });
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(artista -> {
            System.out.println("Artista: " + artista.getNome());
            artista.getMusicas().forEach(musica -> {
                System.out.println("Música: " + musica.getTitulo());
            });
        });

    }

    private void cadastrarMusica() {
        System.out.println("Informe o nome do artista para cadastrar a música: ");
        var nomeArtista = scanner.nextLine();
        Optional<Artista> artistaOptional = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if(artistaOptional.isEmpty()) {
            System.out.println("Artista não encontrado!");
            return;
        } else {
            System.out.println("Informe o nome da música: ");
            var nomeMusica = scanner.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artistaOptional.get());
            artistaOptional.get().getMusicas().add(musica);
            artistaRepository.save(artistaOptional.get());
        }
    }

    private void cadastrarArtista() {
        System.out.println("Informe o nome do artista: ");
        var nome = scanner.nextLine();
        System.out.println("Informe o tipo do artista: (banda, solo ou dupla) ");
        var tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("solo")) {
            tipo = "CANTOR_SOLO";
        }

        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
        Artista artista = new Artista(nome, tipoArtista);
        artistaRepository.save(artista);
        System.out.println("Artista cadastrado com sucesso!");

    }
}
