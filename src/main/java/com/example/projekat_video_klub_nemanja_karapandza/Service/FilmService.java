package com.example.projekat_video_klub_nemanja_karapandza.Service;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Film;
import com.example.projekat_video_klub_nemanja_karapandza.Entities.VideoKlub;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.FilmRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.VideoKlubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FilmService {

    private final FilmRepository filmRepository;
    private final VideoKlubRepository videoKlubRepository;

    public void dodajFilmUBazu(Film film){
        filmRepository.save(film);
    }

    public void dodajFilmUVideoKlub(int idVideoKluba, int  idFilma){
        Boolean postojiVideoKlub = videoKlubRepository.existsById(idVideoKluba);
        Boolean postojiFilm = filmRepository.existsById(idFilma);
        if(postojiVideoKlub && postojiFilm){
            VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
            Film film = filmRepository.findById(idFilma).get();
            videoKlub.getListaFilmova().add(film);
            film.getListaVideoKlubova().add(videoKlub);
            videoKlubRepository.save(videoKlub);
            filmRepository.save(film);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID video kluba ili ID filma ne postoje");
        }
    }

    public List<Film> prikaziSveFilmove(){
        List<Film> listaSvihFilmova = filmRepository.findAll();
        return listaSvihFilmova;
    }

    public List<Film> prikaziFilmoveIzOdredjenogVideoKluba(int idVideoKluba){
        VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
        return videoKlub.getListaFilmova();
    }

    public List<Film> pretragaSvihFilmovaPoImenu(String imeFilma){
        List<Film> listaSvihFilmova = filmRepository.findAll();
        List<Film> nadjeniFilmovi = new ArrayList<>();
        for(Film i:listaSvihFilmova){
            if(i.getIme().equals(imeFilma)){
                nadjeniFilmovi.add(i);
            }
        }
        return nadjeniFilmovi;
    }

    public List<Film> pretragaSvihFilmovaPoZanru(String zanrFilma){
        List<Film> listaSvihFilmova = filmRepository.findAll();
        List<Film> nadjeniFilmovi = new ArrayList<>();
        for(Film i:listaSvihFilmova){
            if(i.getZanr().equals(zanrFilma)){
                nadjeniFilmovi.add(i);
            }
        }
        return nadjeniFilmovi;
    }

    public List<Film> pretragaSvihFilmovaPoGodini(int godinaSnimanja){
        List<Film> listaSvihFilmova = filmRepository.findAll();
        List<Film> nadjeniFilmovi = new ArrayList<>();
        for(Film i:listaSvihFilmova){
            if(i.getGodinaObjavljivanja() == godinaSnimanja){
                nadjeniFilmovi.add(i);
            }
        }
        return nadjeniFilmovi;
    }

    public List<Film> pretragaSvihFilmovaPoCeni(int cenaRentiranja){
        List<Film> listaSvihFilmova = filmRepository.findAll();
        List<Film> nadjeniFilmovi = new ArrayList<>();
        for(Film i:listaSvihFilmova){
            if(i.getCenaRentiranja() == cenaRentiranja){
                nadjeniFilmovi.add(i);
            }
        }
        return nadjeniFilmovi;
    }

    public List prikaziFilmovePoZanru(int idVideoKluba, String zanrFilma){
        VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
        List<Film> pretrazeniFilmovi = new ArrayList<>();
        for(Film i:videoKlub.getListaFilmova()){
            if(i.getZanr().contains(zanrFilma)){
                pretrazeniFilmovi.add(i);
            }
        }
        return pretrazeniFilmovi;
    }

    public List prikaziFilmovePoGodiniSnimanja(String imeVideoKluba, int godinaSnimanjaFilma){
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        List<Film> pretrazeniFilmovi = new ArrayList<>();
        for(Film i:videoKlub.getListaFilmova()){
            if(i.getGodinaObjavljivanja()<=godinaSnimanjaFilma){
                pretrazeniFilmovi.add(i);
            }
        }
        return pretrazeniFilmovi;
    }

    public List prikaziFilmovePoCeniRentiranja(String imeVideoKluba, int cenaRentiranja) {
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        List<Film> pretrazeniFilmovi = new ArrayList<>();
        for(Film i:videoKlub.getListaFilmova()){
            if(i.getCenaRentiranja()<=cenaRentiranja){
                pretrazeniFilmovi.add(i);
            }
        }
        return pretrazeniFilmovi;
    }

    @Modifying
    public void izmeniFilm(Film film, int idFilma){
        Film filmIzBaze = filmRepository.findById(idFilma).get();
        filmIzBaze.setIme(film.getIme());
        filmIzBaze.setZanr((film.getZanr()));
        filmIzBaze.setGodinaObjavljivanja(film.getGodinaObjavljivanja());
        filmIzBaze.setCenaRentiranja(film.getCenaRentiranja());
        filmRepository.save(filmIzBaze);
    }

    public void obrisiFilm (int idFilma){
        filmRepository.findById(idFilma);
        filmRepository.deleteById(idFilma);
    }

    public void obrisiFilmIzOdredjenogVideoKluba(int idVideoKluba, int idFilma){
        Boolean postojiVideoKlub = videoKlubRepository.existsById(idVideoKluba);
        Boolean postojiFilm = filmRepository.existsById(idFilma);
        if(postojiVideoKlub && postojiFilm){
            VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
            Film film = filmRepository.findById(idFilma).get();
            videoKlub.getListaFilmova().remove(film);
            film.getListaVideoKlubova().remove(videoKlub);
            videoKlubRepository.save(videoKlub);
            filmRepository.save(film);
        }
    }




}
