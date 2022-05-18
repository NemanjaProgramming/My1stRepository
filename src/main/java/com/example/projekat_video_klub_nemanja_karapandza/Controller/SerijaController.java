package com.example.projekat_video_klub_nemanja_karapandza.Controller;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Serija;
import com.example.projekat_video_klub_nemanja_karapandza.Service.SerijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class SerijaController {

    private final SerijaService serijaService;

    @GetMapping("/showAllSerije")                       //radi
    private List<Serija> prikaziSveSerije(){
        return serijaService.prikaziSveSerije();
    }

    @GetMapping("/showAllByImeSerije/{imeSerije}")             //radi ali izbaci rezultat 6000 puta
    public List prikaziSvePoImenu(@PathVariable("imeSerije") String imeSerije){
        return serijaService.pretragaSvihSerijaPoImenu(imeSerije);
    }

    @GetMapping("/showAllByZanrSerije/{zanrSerije}")            //radi ali izbaci rezultat 6000 puta
    public List prikaziSvePoZanru(@PathVariable("zanrSerije") String zanrSerije){
        return serijaService.pretragaSvihSerijaPoZanru(zanrSerije);
    }

    @GetMapping("/showAllByGodinaSnimanjaSerije/{godinaSnimanjaSerije}")   //radi ali izbaci rezultat 6000 puta
    public List<Serija> prikaziSvePoGodiniSnimanja(@PathVariable("godinaSnimanjaSerije") int godinaSnimanjaSerije){
        return serijaService.pretragaSvihSerijaPoGodiniSnimanja(godinaSnimanjaSerije);
    }

    @GetMapping("/showAllByCenaRentiranjaSerije/{cenaRentiranjaSerije}")       //radi ali izbaci rezultat 6000 puta
    public List<Serija> prikaziSvePoCeniRentiranja(@PathVariable("cenaRentiranjaSerije") int cenaRentiranjaSerije){
        return serijaService.pretragaSvihSerijaPoCeni(cenaRentiranjaSerije);
    }

    @GetMapping("/showSerijaFromVideoKlub/{idVideoKluba}")
    public List<Serija> prikaziSerijeIzOdredjenogVideoKluba(@PathVariable("idVideoKluba") int idVideoKluba){
        return serijaService.prikaziSerijeIzOdredjenogVideoKluba(idVideoKluba);
    }

    @GetMapping("/showSerijeByZanr/{imeVideoKluba}/{zanrSerije}")
    public List<Serija> prikaziSerijePoZanru(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("zanrSerije") String zanrSerije){
        return serijaService.prikaziSerijePoZanru(imeVideoKluba, zanrSerije);
    }

    @GetMapping("/showSerijeByGodinaSnimanja/{imeVideoKluba}/{godinaSnimanja}")
    public List<Serija> prikaziSerijePoGodiniSnimanja(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("godinaSnimanja") int godinaSnimanja){
        return serijaService.prikaziSerijePoGodiniSnimanja(imeVideoKluba,godinaSnimanja);
    }

    @GetMapping("/showSerijePoCeniRentiranja/{imeVideoKluba}/{cenaRentiranja}")
    public List<Serija> prikaziSerijePoCeniRentiranja(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("cenaRentiranja") int cenaRentiranja){
        return serijaService.prikaziSerijePoCeniRentiranja(imeVideoKluba, cenaRentiranja);
    }

    @PostMapping("/addSerijuUBazu")
    public void dodajSerijuUBazu(@RequestBody Serija serija){
        serijaService.dodajSerijuUBazu(serija);
    }

    @PostMapping("/addSerijuUVideoKlub/{idVideoKluba}/{imeSerije}")
    public void dodajSerijuUVideoKlub(@PathVariable("idVideoKluba") int idVideoKluba,@PathVariable("imeSerije") String imeSerije){
        serijaService.dodajSerijuUVideoKlub(idVideoKluba, imeSerije);
    }

    @PutMapping("/changeSerija/{idSerije}")
    public void izmeniSeriju(@RequestBody Serija serija, @PathVariable ("idSerije") int idSerije){
        serijaService.izmeniSeriju(serija,idSerije);
    }

    @DeleteMapping("/deleteSeriju/{idSerije}")
    public void obrisiSeriju(@PathVariable("idSerije") int idSerije){
        serijaService.obrisiSeriju(idSerije);
    }

    @DeleteMapping("/deleteSerijuIzVideoKluba/{idVideoKluba}/{idSerije}")
    public void obrisiSerijuIzVideoKluba(@PathVariable("idVideoKluba") int idVideoKluba,@PathVariable("idSerije") int idSerije){
        serijaService.obrisiSerijuIzOdredjenogVideoKluba(idVideoKluba, idSerije);
    }





}
