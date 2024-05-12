package br.com.jsergio.musicSave.model;

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
    private TipoArtista tipo_artista;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    public Artista() {
    }

    public Artista(String nome, TipoArtista tipoArtista) {
        this.nome = nome;
        this.tipo_artista = tipoArtista;
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

    public TipoArtista getTipo_artista() {
        return tipo_artista;
    }

    public void setTipo_artista(TipoArtista tipo_artista) {
        this.tipo_artista = tipo_artista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return  "nome='" + nome + '\'' +
                ", tipo_artista=" + tipo_artista +
                ", musicas=" + musicas;

    }
}
