package com.example.projekat_video_klub_nemanja_karapandza.Service;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Serija;
import com.example.projekat_video_klub_nemanja_karapandza.Entities.VideoKlub;
import com.example.projekat_video_klub_nemanja_karapandza.Repository.SerijaRepository;
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

public class SerijaService {

    private final SerijaRepository serijaRepository;
    private final VideoKlubRepository videoKlubRepository;

    public void dodajSerijuUBazu(Serija serija){
        serijaRepository.save(serija);
    }

    public void dodajSerijuUVideoKlub(int idVideoKluba, String imeSerije){
        Boolean postojiVideoKlub = videoKlubRepository.existsById(idVideoKluba);
        Boolean postojiSerija = serijaRepository.existsByIme(imeSerije);
        if(postojiVideoKlub && postojiSerija){
            VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
            Serija serija = serijaRepository.findByIme(imeSerije);
            videoKlub.getListaSerija().add(serija);
            serija.getListaVideoKlubova().add(videoKlub);
            videoKlubRepository.save(videoKlub);
            serijaRepository.save(serija);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID video kluba ili naziv filma ne postoje");
        }
    }

    public List<Serija> prikaziSveSerije(){
        List<Serija> listaSvihSerija = serijaRepository.findAll();
        return listaSvihSerija;
    }

    public List prikaziSerijeIzOdredjenogVideoKluba(int idVideoKluba){
        VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
        return videoKlub.getListaSerija();
    }

    public List<Serija> pretragaSvihSerijaPoImenu(String imeSerije){
        List<Serija> listaSvihSerija = serijaRepository.findAll();
        List<Serija> nadjeneSerije = new ArrayList<>();
        for(Serija i:listaSvihSerija){
            if(i.getIme().equals(imeSerije)){
                nadjeneSerije.add(i);
            }
        }
        return nadjeneSerije;
    }

    public List<Serija> pretragaSvihSerijaPoZanru(String zanrSerije){
        List<Serija> listaSvihSerija = serijaRepository.findAll();
        List<Serija> nadjeneSerije = new ArrayList<>();
        for(Serija i:listaSvihSerija){
            if(i.getZanr().equals(zanrSerije)){
                nadjeneSerije.add(i);
            }
        }
        return nadjeneSerije;
    }

    public List<Serija> pretragaSvihSerijaPoGodiniSnimanja(int godinaSnimanja){
        List<Serija> listaSvihSerija = serijaRepository.findAll();
        List<Serija> nadjeneSerije = new ArrayList<>();
        for(Serija i:listaSvihSerija){
            if(i.getGodina() == godinaSnimanja){
                nadjeneSerije.add(i);
            }
        }
        return nadjeneSerije;
    }

    public List<Serija> pretragaSvihSerijaPoCeni(int cenaRentiranja){
        List<Serija> listaSvihSerija = serijaRepository.findAll();
        List<Serija> nadjeneSerije = new ArrayList<>();
        for(Serija i:listaSvihSerija){
            if(i.getGodina() == cenaRentiranja){
                nadjeneSerije.add(i);
            }
        }
        return nadjeneSerije;
    }


    public List prikaziSerijePoZanru(String imeVideoKluba, String zanrSerije){
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        List<Serija> pretrazeneSerije = new ArrayList<>();
        for(Serija i:videoKlub.getListaSerija()){
            if(i.getZanr().contains(zanrSerije)){
                pretrazeneSerije.add(i);
            }
        }
        return pretrazeneSerije;
    }

    public List prikaziSerijePoGodiniSnimanja(String imeVideoKluba, int godinaSnimanjaSerije){
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        List<Serija> pretrazeneSerije = new ArrayList<>();
        for(Serija i:videoKlub.getListaSerija()){
            if(i.getGodina()==godinaSnimanjaSerije){
                pretrazeneSerije.add(i);
            }
        }
        return pretrazeneSerije;
    }

    public List prikaziSerijePoCeniRentiranja(String imeVideoKluba, int cenaRentiranja) {
        VideoKlub videoKlub = videoKlubRepository.findByIme(imeVideoKluba);
        List<Serija> pretrazeneSerije = new ArrayList<>();
        for(Serija i:videoKlub.getListaSerija()){
            if(i.getCenaRentiranja()<=cenaRentiranja){
                pretrazeneSerije.add(i);
            }
        }
        return pretrazeneSerije;
    }

    @Modifying
    public void izmeniSeriju(Serija serija, int idSerije){
        Serija serijaIzBaze = serijaRepository.findById(idSerije).get();
        serijaIzBaze.setIme(serija.getIme());
        serijaIzBaze.setZanr((serija.getZanr()));
        serijaIzBaze.setGodina(serija.getGodina());
        serijaIzBaze.setCenaRentiranja(serija.getCenaRentiranja());
        serijaRepository.save(serijaIzBaze);
    }

    public void obrisiSeriju (int idSerije){
        serijaRepository.findById(idSerije);
        serijaRepository.deleteById(idSerije);
    }

    public void obrisiSerijuIzOdredjenogVideoKluba(int idVideoKluba, int idSerije){
        Boolean postojiVideoKlub = videoKlubRepository.existsById(idVideoKluba);
        Boolean postojiSerija = serijaRepository.existsById(idSerije);
        if(postojiVideoKlub && postojiSerija){
            VideoKlub videoKlub = videoKlubRepository.findById(idVideoKluba).get();
            Serija serija = serijaRepository.findById(idSerije).get();
            videoKlub.getListaSerija().remove(serija);
            serija.getListaVideoKlubova().remove(videoKlub);
            videoKlubRepository.save(videoKlub);
            serijaRepository.save(serija);
        }
    }


}
