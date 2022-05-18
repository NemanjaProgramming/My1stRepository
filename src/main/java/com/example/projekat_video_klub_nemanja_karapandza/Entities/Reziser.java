package com.example.projekat_video_klub_nemanja_karapandza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reziser")
@RequiredArgsConstructor
@Getter @Setter

public class Reziser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "nacionalnost")
    private String nacionalnost;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Film> listaFilmova;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "listaRezisera")
    private List<Serija> listaSerija;
}