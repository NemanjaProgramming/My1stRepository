package com.example.projekat_video_klub_nemanja_karapandza.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "film")
@RequiredArgsConstructor
@Getter @Setter

public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "zanr")
    private String zanr;

    @Column(name = "godina_objavljivanja")
    private int godinaObjavljivanja;

    @Column(name = "cena_rentiranja")
    private int cenaRentiranja;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "listaFilmova")
    private List<VideoKlub> listaVideoKlubova;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "listaFilmova")
    private List<Reziser> listaRezisera;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "listaFilmova")
    private List<Glumac> listaGlumaca;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "film")
    private List<Ocena> listaOcena;

    @ManyToOne(fetch = FetchType.LAZY)
    private Klijent klijent;



}
