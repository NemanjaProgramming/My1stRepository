package com.example.projekat_video_klub_nemanja_karapandza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "klijent")
@RequiredArgsConstructor
@Getter @Setter

public class Klijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "godina_rodjenja")
    private int godinaRodjenja;

    @Column(name = "budzet")
    private int budzet;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private VideoKlub videoKlub;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "klijent")
    private List<Film> listaFilmova;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "klijent")
    private List<Serija> listaSerija;
}
