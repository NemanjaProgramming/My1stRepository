package com.example.projekat_video_klub_nemanja_karapandza.Service;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Film;
import com.example.projekat_video_klub_nemanja_karapandza.Entities.Klijent;
import com.example.projekat_video_klub_nemanja_karapandza.Entities.Serija;
import com.example.projekat_video_klub_nemanja_karapandza.Entities.VideoKlub;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.FilmRepository;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.KlijentRepository;
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

public class VideoklubService {

    private final VideoKlubRepository videoKlubRepository;
    private final FilmRepository filmRepository;
    private final KlijentRepository klijentRepository;
    private final SerijaRepository serijaRepository;

    public void dodajVideoKlub(VideoKlub videoKlub){
        videoKlubRepository.save(videoKlub);
    }

    public List<VideoKlub> prikaziSveVideoKlubove(){
        List<VideoKlub> listaKlubova = videoKlubRepository.findAll();
        return listaKlubova;
    }

    @Modifying
    public void iznajmiFilm(int idKlijenta, int idFilma){
        Boolean postojiFilm = filmRepository.existsById(idFilma);
        Boolean postojiKlijent = klijentRepository.existsById(idKlijenta);
        if(postojiFilm && postojiKlijent){
            Klijent klijent = klijentRepository.findById(idKlijenta).get();
            Film film = filmRepository.findById(idFilma).get();
            VideoKlub videoKlub = klijent.getVideoKlub();
            if(videoKlub.getListaFilmova().contains(film)) {
                    if (klijent.getBudzet() >= film.getCenaRentiranja()) {
                        System.out.println(" Klijent po Id=jem " + klijent.getId() + " je rentirao film pod Id=jem " + film.getId());
                        klijent.setBudzet(klijent.getBudzet() - film.getCenaRentiranja());
                        videoKlub.setRacunUBanci(videoKlub.getRacunUBanci() + film.getCenaRentiranja());
                        film.setKlijent(klijent);
                        klijentRepository.save(klijent);
                        filmRepository.save(film);
                    }
            }
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Klijent ili Film pod odredjenim Id-jem ne postoje");
        }
    }

    @Modifying
    public void iznajmiSeriju(int idKlijenta, int idSerije) {
        Boolean postojiSerija = serijaRepository.existsById(idSerije);
        Boolean postojiKlijent = klijentRepository.existsById(idKlijenta);
        if (postojiSerija && postojiKlijent) {
            Klijent klijent = klijentRepository.findById(idKlijenta).get();
            Serija serija = serijaRepository.findById(idSerije).get();
            VideoKlub videoKlub = klijent.getVideoKlub();
            if (videoKlub.getListaSerija().contains(serija)) {
                if (klijent.getBudzet() >= serija.getCenaRentiranja()) {
                    System.out.println(" Klijent po Id=jem " + klijent.getId() + " je rentirao seriju pod Id=jem " + serija.getId());
                    klijent.setBudzet(klijent.getBudzet() - serija.getCenaRentiranja());
                    videoKlub.setRacunUBanci(videoKlub.getRacunUBanci() + serija.getCenaRentiranja());
                    serija.setKlijent(klijent);
                    klijentRepository.save(klijent);
                    serijaRepository.save(serija);
                }
            }
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Klijent ili serija pod odredjenim Id-jem ne postoje");
            }
        }

    @Modifying
    public void promeniVideoKlub(VideoKlub videoKlub, String imeVideoKluba){
        VideoKlub videoKlubIzBaze = videoKlubRepository.findByIme(imeVideoKluba);
        videoKlubIzBaze.setIme(videoKlub.getIme());
        videoKlubIzBaze.setLokacija(videoKlub.getLokacija());
        videoKlubIzBaze.setRacunUBanci(videoKlub.getRacunUBanci());
        videoKlubRepository.save(videoKlubIzBaze);
    }

    public void obrisiVideoKlub(int idVideoKluba){
        VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
        if((videoKlub.getListaKlijenta().isEmpty()) && (videoKlub.getListaFilmova().isEmpty()) && (videoKlub.getListaSerija().isEmpty())) {
            videoKlubRepository.deleteById(idVideoKluba);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Lista Klijenata, Filmova ili Serija nije prazna. Nije moguce obrisati Video Klub");
        }
    }

}
