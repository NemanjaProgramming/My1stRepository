package com.example.projekat_video_klub_nemanja_karapandza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "serija")
@RequiredArgsConstructor
@Getter @Setter

public class Serija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "zanr")
    private String zanr;

    @Column(name = "broj_sezona")
    private int brojSezona;

    @Column(name = "godina")
    private int godina;

    @Column(name = "cena_rentiranja")
    private int cenaRentiranja;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "listaSerija")
    private List<VideoKlub> listaVideoKlubova;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reziser> listaRezisera;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Glumac> listaGlumaca;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "serija")
    private List<Ocena> listaOcena;

    @ManyToOne(fetch = FetchType.LAZY)
    private Klijent klijent;

}
