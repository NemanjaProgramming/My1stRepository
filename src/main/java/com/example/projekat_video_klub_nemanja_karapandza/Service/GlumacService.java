package com.example.projekat_video_klub_nemanja_karapandza.Service;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.*;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.FilmRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.GlumacRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.SerijaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class GlumacService {

    private final GlumacRepository glumacRepository;
    private final FilmRepository filmRepository;
    private final SerijaRepository serijaRepository;

    public void dodajGlumcaUBazu(Glumac glumac){
        glumacRepository.save(glumac);
    }

    public void dodajGlumcaUFilm(int idFilma, int  idGlumca){
        Boolean postojiFilm = filmRepository.existsById(idFilma);
        Boolean postojiGlumac = glumacRepository.existsById(idGlumca);
        if(postojiGlumac && postojiFilm){
            Film film = filmRepository.findById(idFilma).get();
            Glumac glumac = glumacRepository.findById(idGlumca).get();
            film.getListaGlumaca().add(glumac);
            glumac.getListaFilmova().add(film);
            glumacRepository.save(glumac);
            filmRepository.save(film);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID glumca ili Id Filma ne postoje");
        }
    }

    public void dodajGlumcaUSeriju(int idSerije, int  idGlumca){
        Boolean postojiSerija = serijaRepository.existsById(idSerije);
        Boolean postojiGlumac = glumacRepository.existsById(idGlumca);
        if(postojiGlumac && postojiSerija){
            Serija serija = serijaRepository.findById(idSerije).get();
            Glumac glumac = glumacRepository.findById(idGlumca).get();
            serija.getListaGlumaca().add(glumac);
            glumac.getListaSerija().add(serija);
            glumacRepository.save(glumac);
            serijaRepository.save(serija);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID glumca ili Id serije ne postoje");
        }
    }

    public List<Glumac> prikaziSveGlumce(){
        List<Glumac> listaSvihGlumaca = glumacRepository.findAll();
        return listaSvihGlumaca;
    }

    public List<Glumac> pretragaGlumaca(String searchParam){
        List<Glumac> listaGlumaca = glumacRepository.pretraziGlumce(searchParam);
        return listaGlumaca;
    }

    public List prikaziGlumceIzOdredjenogFilma(int idFilma){
        Film film = filmRepository.findById(idFilma).get();
        return film.getListaGlumaca();
    }

    public List prikaziGlumceIzOdredjeneSerije(int idSerije){
        Serija serija = serijaRepository.findById(idSerije).get();
        return serija.getListaGlumaca();
    }

    @Modifying
    public void izmeniGlumca(Glumac glumac, int idGlumca){
        Glumac glumacIzBaze = glumacRepository.findById(idGlumca).get();
        glumacIzBaze.setIme(glumac.getIme());
        glumacIzBaze.setPrezime(glumac.getPrezime());
        glumacIzBaze.setGodinaRodjenja(glumac.getGodinaRodjenja());
        glumacIzBaze.setNacionalnost(glumac.getNacionalnost());
        glumacRepository.save(glumacIzBaze);
    }

    public void obrisiGlumca (int idGlumca){
        glumacRepository.findById(idGlumca);
        glumacRepository.deleteById(idGlumca);
    }


}
