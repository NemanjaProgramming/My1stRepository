package com.example.projekat_video_klub_nemanja_karapandza.Controller;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Reziser;
import com.example.projekat_video_klub_nemanja_karapandza.Service.ReziserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class ReziserController {

    private final ReziserService reziserService;

    @GetMapping("/showAllRezisere")
    public List<Reziser> prikaziSveRezisere() {
        return reziserService.prikaziSveRezisere();
    }

    @GetMapping("/showPoParametruReziser/{searchParam}")
    public List prikaziPoUnetomParametru(@PathVariable("searchParam") String searchParam){
        return reziserService.pretragaRezisera(searchParam);
    }

    @GetMapping("/showReziserFromFilm/{idFilma}")
    public List prikaziReziseraIzOdredjenogFilma(@PathVariable ("idFilma") int idFilma){
        return reziserService.prikaziRezisereIzOdredjenogFilma(idFilma);
    }

    @GetMapping("/showRezisereFromSerija/{idSerije}")
    public List prikaziRezisereIzOdredjeneSerije(@PathVariable ("idSerije") int idSerije){
        return reziserService.prikaziRezisereIzOdredjeneSerije(idSerije);
    }

    @PostMapping("/addReziseraUBazu")
    public void dodajReziseraUBazu(@RequestBody Reziser reziser){
        reziserService.dodajReziseraUBazu(reziser);
    }

    @PostMapping("/addReziseraUFilm/{idFilma}/{idRezisera}")
    public void dodajReziseraUFilm(@PathVariable ("idFilma") int idFilma, @PathVariable ("idRezisera") int idRezisera){
        reziserService.dodajReziseraUFilm(idFilma, idRezisera);
    }

    @PostMapping("/addReziseraUSeriju/{idSerije}/{idRezisera}")
    public void dodajReziseraUSeriju(@PathVariable ("idSerije") int idSerije, @PathVariable ("idRezisera") int idRezisera){
        reziserService.dodajReziseraUSeriju(idSerije, idRezisera);
    }

    @PutMapping("/changeRezisera/{idRezisera}")
    public void izmeniRezisera(@RequestBody Reziser reziser, @PathVariable ("idRezisera") int idRezisera){
        reziserService.izmeniRezisera(reziser,idRezisera);
    }

    @DeleteMapping("/deleteReziser/{idRezisera}")
    public void obrisiRezisera(@PathVariable ("idRezisera") int idRezisera){
        reziserService.obrisiRezisera(idRezisera);
    }




}
