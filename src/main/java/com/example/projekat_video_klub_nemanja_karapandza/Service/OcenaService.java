package com.example.projekat_video_klub_nemanja_karapandza.Service;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.*;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.FilmRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.OcenaRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.SerijaRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.VideoKlubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class OcenaService {

    private final OcenaRepository ocenaRepository;
    private final FilmRepository filmRepository;
    private final SerijaRepository serijaRepository;
    private final VideoKlubRepository videoKlubRepository;


    public List<Ocena> prikaziSveOcene(){
        List<Ocena> listaSvihOcena = ocenaRepository.findAll();
        return listaSvihOcena;
    }

    public void oceniFilm(Ocena ocena, int idFilma){
        Boolean postojiFilm = filmRepository.existsById(idFilma);
        if(postojiFilm){
            Film film = filmRepository.findById(idFilma).get();
            film.getListaOcena().add(ocena);
            ocena.setFilm(film);
            ocenaRepository.save(ocena);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id filma ne postoji");
        }
    }

    public String vratiProsecnuOcenuIzFilma(int idFilma){
        Boolean postojiFilm = filmRepository.existsById(idFilma);
        int zbir = 0;
        if(postojiFilm){
            Film film = filmRepository.findById(idFilma).get();
            List<Ocena> listaOcena = film.getListaOcena();
            for(Ocena i:listaOcena){
                zbir = zbir + i.getOcena();
            }
            zbir = zbir/ listaOcena.size();
        }
        return "Prosecna ocena je: " + zbir;
    }

    public void oceniSeriju(Ocena ocena, String imeSerije){
        Boolean postojiSerija = serijaRepository.existsByIme(imeSerije);
        if(postojiSerija){
            Serija serija  = serijaRepository.findByIme(imeSerije);
            serija.getListaOcena().add(ocena);
            ocena.setSerija(serija);
            ocenaRepository.save(ocena);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id serije ne postoji");
        }
    }

    public void oceniVideoKlub(Ocena ocena, String imeVideoKluba){
        Boolean postojiVideoklub = videoKlubRepository.existsByIme(imeVideoKluba);
        if(postojiVideoklub){
            VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
            videoKlub.getListaOcena().add(ocena);
            ocena.setVideoKlub(videoKlub);
            ocenaRepository.save(ocena);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id video kluba ne postoji");
        }
    }

    public List prikaziOceneFilma(String imeFilma){
        Film film = filmRepository.findByIme(imeFilma);
        return film.getListaOcena();
    }

    public List prikaziOceneSerije(String imeSerije){
        Serija serija = serijaRepository.findByIme(imeSerije);
        return serija.getListaOcena();
    }

    public List prikaziOceneVideoKluba(String imeVideoKluba){
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        return videoKlub.getListaOcena();
    }

    @Modifying
    public void izmeniOcenu(Ocena ocena, int idOcene){
        Ocena ocenaIzBaze = ocenaRepository.findById(idOcene).get();
        ocenaIzBaze.setKomentar(ocena.getKomentar());
        ocenaIzBaze.setOcena(ocena.getOcena());
        ocenaRepository.save(ocenaIzBaze);
    }

    public void obrisiOcenuFilma(String imeFilma, int idOcene){
        Boolean postojiFilm = filmRepository.existsByIme(imeFilma);
        Boolean postojiOcena = ocenaRepository.existsById(idOcene);
        if(postojiFilm && postojiOcena){
            Film film = filmRepository.findByIme(imeFilma);
            Ocena ocena = ocenaRepository.findById(idOcene).get();
            film.getListaOcena().remove(ocena);
            filmRepository.save(film);
        }
    }

    public void obrisiOcenuSerije(String imeSerije, int idOcene){
        Boolean postojiSerija = serijaRepository.existsByIme(imeSerije);
        Boolean postojiOcena = ocenaRepository.existsById(idOcene);
        if(postojiSerija && postojiOcena){
            Serija serija = serijaRepository.findByIme(imeSerije);
            Ocena ocena = ocenaRepository.findById(idOcene).get();
            serija.getListaOcena().remove(ocena);
            serijaRepository.save(serija);
        }
    }

    public void obrisiOcenuVideokluba(String imeVideoKluba, int idOcene){
        Boolean postojiVideoKlub = videoKlubRepository.existsByIme(imeVideoKluba);
        Boolean postojiOcena = ocenaRepository.existsById(idOcene);
        if(postojiVideoKlub && postojiOcena){
            VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
            Ocena ocena = ocenaRepository.findById(idOcene).get();
            videoKlub.getListaOcena().remove(ocena);
            videoKlubRepository.save(videoKlub);
        }
    }


}
