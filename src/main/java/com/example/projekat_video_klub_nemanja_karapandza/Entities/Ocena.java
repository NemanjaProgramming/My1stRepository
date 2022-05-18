package com.example.projekat_video_klub_nemanja_karapandza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ocena")
@RequiredArgsConstructor
@Getter @Setter

public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "komentar")
    private String komentar;

    @Column(name = "ocena")
    private int ocena;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private VideoKlub videoKlub;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Serija serija;

}