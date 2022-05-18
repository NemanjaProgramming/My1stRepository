package com.example.projekat_video_klub_nemanja_karapandza.Controller;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Klijent;
import com.example.projekat_video_klub_nemanja_karapandza.Service.KlijentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class KlijentController {

    private final KlijentService klijentService;

    @GetMapping("/showAllKlijente")
    public List<Klijent> prikaziSveKlijente(){
        return klijentService.prikaziSveKlijente();
    }

    @GetMapping("/showKlijenteFromVideoKlub/{imeVideoKluba}")
    public List<Klijent> prikaziKlijenteIzOdredjenogVideoKluba(@PathVariable("imeVideoKluba") String imeVideoKluba){
        return klijentService.prikaziKlijenteUOdredjenomVideoKlubu(imeVideoKluba);
    }

    @GetMapping("/showKlijenteByIme/{imeVideoKluba}/{imeKlijenta}")
    public List<Klijent> prikaziKlijentePoImenu(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("imeKlijenta") String imeKlijenta){
        return klijentService.prikaziKlijetaPoImenu(imeVideoKluba, imeKlijenta);
    }

    @GetMapping("/showKlijeteByGodinaRodjenja/{imeVideoKluba}/{godinaRodjenja}")
    public List<Klijent> prikaziKlijentePoGodiniRodjenja(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("godinaRodjenja") int godinaRodjenja){
        return klijentService.pretraziKlijentaPoGodiniRodjenja(imeVideoKluba, godinaRodjenja);
    }

    @PostMapping("/addKlijetaUBazu")
    public void dodajKlijentaUBazu(@RequestBody Klijent klijent){
        klijentService.dodajKlijentaUBazu(klijent);
    }

    @PostMapping("/addKlijentaUVideoKlub/{idVideoKluba}/{idKlijenta}")
    public void dodajKlijentaUVideoKlub(@PathVariable("idVideoKluba") int idVideoKluba,@PathVariable("idKlijenta") int idKlijenta){
        klijentService.dodajKlijentaUVideoKlub(idVideoKluba, idKlijenta);
    }

    @PutMapping("/changeKlijenta/{idKlijenta}")
    public void izmeniKlijenta(@RequestBody Klijent klijent, @PathVariable ("idKlijenta") int idKlijenta){
        klijentService.izmeniklijenta(klijent,idKlijenta);
    }

    @DeleteMapping("/deleteKlijenta/{idKlijenta}")
    public void obrisiKlijenta(@PathVariable("idKlijenta") int idKlijenta){
        klijentService.obrisiKlijenta(idKlijenta);
    }




}
