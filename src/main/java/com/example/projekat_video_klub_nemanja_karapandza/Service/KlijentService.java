package com.example.projekat_video_klub_nemanja_karapandza.Service;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Klijent;
import com.example.projekat_video_klub_nemanja_karapandza.Entities.VideoKlub;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.KlijentRepository;
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

public class KlijentService {

    private final KlijentRepository klijentRepository;
    private final VideoKlubRepository videoKlubRepository;

    public void dodajKlijentaUBazu(Klijent klijent){
        klijentRepository.save(klijent);
    }

    public void dodajKlijentaUVideoKlub(int idVideoKluba, int idKlijenta){
        Boolean postojiVideoKlub = videoKlubRepository.existsById(idVideoKluba);
        Boolean postojiKlijent = klijentRepository.existsById(idKlijenta);
        if(postojiVideoKlub && postojiKlijent){
            VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
            Klijent klijent = klijentRepository.findById(idKlijenta).get();
            videoKlub.getListaKlijenta().add(klijent);
            klijent.setVideoKlub(videoKlub);
            klijentRepository.save(klijent);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID video kluba ili ID klijenta su nepostojeci");
        }
    }

    public List<Klijent> prikaziSveKlijente(){
        List<Klijent> listaSvihKlijenata = klijentRepository.findAll();
        return listaSvihKlijenata;
    }

    public List prikaziKlijenteUOdredjenomVideoKlubu(String imeVideoKluba){
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        return videoKlub.getListaKlijenta();
    }

    public List prikaziKlijetaPoImenu(String imeVideoKluba, String imeKlijenta){
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        List<Klijent> pretrazeniKlijenti = new ArrayList<>();
        for(Klijent i:videoKlub.getListaKlijenta()){
            if(i.getIme().contains(imeKlijenta)){
                pretrazeniKlijenti.add(i);
            }
        }
        return pretrazeniKlijenti;
    }

    public List pretraziKlijentaPoGodiniRodjenja(String imeVideoKluba, int godinaRodjenja){
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        List<Klijent> pretrazeniKlijenti = new ArrayList<>();
        for(Klijent i:videoKlub.getListaKlijenta()){
            if(i.getGodinaRodjenja()==godinaRodjenja){
                pretrazeniKlijenti.add(i);
            }
        }
        return pretrazeniKlijenti;
    }

    @Modifying
    public void izmeniklijenta(Klijent klijent, int idKlijenta){
        Klijent klijentIzBaze = klijentRepository.findById(idKlijenta).get();
        klijentIzBaze.setIme(klijent.getIme());
        klijentIzBaze.setGodinaRodjenja(klijent.getGodinaRodjenja());
        klijentIzBaze.setBudzet(klijent.getBudzet());
        klijentRepository.save(klijentIzBaze);
    }

    public void obrisiKlijenta(int idKlijenta){
        klijentRepository.findById(idKlijenta);
        klijentRepository.deleteById(idKlijenta);
    }

}
