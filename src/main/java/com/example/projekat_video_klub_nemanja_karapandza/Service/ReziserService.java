package com.example.projekat_video_klub_nemanja_karapandza.Service;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.*;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.FilmRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.ReziserRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.SerijaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ReziserService {

    private final ReziserRepository reziserRepository;
    private final FilmRepository filmRepository;
    private final SerijaRepository serijaRepository;

    public void dodajReziseraUBazu(Reziser reziser) {
        reziserRepository.save(reziser);
    }

    public List<Reziser> prikaziSveRezisere(){
        List<Reziser> listaSvihRezisera = reziserRepository.findAll();
        return listaSvihRezisera;
    }

    public List<Reziser> pretragaRezisera(String searchParam){
        List<Reziser> listaRezisera = reziserRepository.pretraziRezisere(searchParam);
        return listaRezisera;
    }

    public List prikaziRezisereIzOdredjenogFilma(int idFilma){
        Film film = filmRepository.findById(idFilma).get();
        return film.getListaRezisera();
    }

    public List prikaziRezisereIzOdredjeneSerije(int idSerije){
        Serija serija = serijaRepository.findById(idSerije).get();
        return serija.getListaRezisera();
    }

    public void dodajReziseraUFilm(int idFilma, int  idRezisera){
        Boolean postojiFilm = filmRepository.existsById(idFilma);
        Boolean postojiReziser = reziserRepository.existsById(idRezisera);
        if(postojiReziser && postojiFilm){
            Film film = filmRepository.findById(idFilma).get();
            Reziser reziser = reziserRepository.findById(idRezisera).get();
            film.getListaRezisera().add(reziser);
            reziser.getListaFilmova().add(film);
            reziserRepository.save(reziser);
            filmRepository.save(film);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Film ili Reziser pod navedenim Id-jem ne postoje");
        }
    }

    public void dodajReziseraUSeriju(int idSerije, int  idRezisera){
        Boolean postojiSerija = serijaRepository.existsById(idSerije);
        Boolean postojiReziser = reziserRepository.existsById(idRezisera);
        if(postojiReziser && postojiSerija){
            Serija serija = serijaRepository.findById(idSerije).get();
            Reziser reziser = reziserRepository.findById(idRezisera).get();
            serija.getListaRezisera().add(reziser);
            reziser.getListaSerija().add(serija);
            reziserRepository.save(reziser);
            serijaRepository.save(serija);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Serija ili Reziser pod navedenim Id-jem ne postoje");
        }
    }

    @Modifying
    public void izmeniRezisera(Reziser reziser, int idRezisera){
        Reziser reziserIzBaze = reziserRepository.findById(idRezisera).get();
        reziserIzBaze.setIme(reziser.getIme());
        reziserIzBaze.setPrezime(reziser.getPrezime());
        reziserIzBaze.setNacionalnost(reziser.getNacionalnost());
        reziserRepository.save(reziserIzBaze);
    }

    public void obrisiRezisera (int idRezisera){
        reziserRepository.findById(idRezisera);
        reziserRepository.deleteById(idRezisera);
    }




}
