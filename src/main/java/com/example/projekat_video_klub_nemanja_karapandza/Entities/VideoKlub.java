package com.example.projekat_video_klub_nemanja_karapandza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "video_klub")
@RequiredArgsConstructor
@Getter @Setter

public class VideoKlub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "racun_u_banci")
    private double racunUBanci;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "videoKlub")
    private List<Klijent> listaKlijenta;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Film> listaFilmova;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Serija> listaSerija;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "videoKlub")
    private List<Ocena> listaOcena;


}
