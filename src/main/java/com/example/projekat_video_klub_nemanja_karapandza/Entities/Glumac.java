package com.example.projekat_video_klub_nemanja_karapandza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "glumac")
@RequiredArgsConstructor
@Getter @Setter

public class Glumac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "godina_rodjenja")
    private int godinaRodjenja;

    @Column(name = "nacionalnost")
    private String nacionalnost;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Film> listaFilmova;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "listaGlumaca")
    @JsonIgnore
    private List<Serija> listaSerija;


}
