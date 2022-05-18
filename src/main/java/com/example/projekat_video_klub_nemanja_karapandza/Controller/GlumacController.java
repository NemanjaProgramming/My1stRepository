package com.example.projekat_video_klub_nemanja_karapandza.Controller;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Glumac;
import com.example.projekat_video_klub_nemanja_karapandza.Service.GlumacService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class GlumacController {

    private final GlumacService glumacService;

    @GetMapping("/showAllGlumce")
    public List<Glumac> prikaziSveGlumce() {
        return glumacService.prikaziSveGlumce();
    }

    @GetMapping("/showPoParametruGlumac/{searchParam}")
    public List<Glumac> prikaziPoUnetomParametru(@PathVariable("searchParam") String searchParam){
        return glumacService.pretragaGlumaca(searchParam);
    }

    @GetMapping("/showGlumceFromFilm/{idFilma}")
    public List<Glumac> prikaziGlumcaIzOdredjenogFilma(@PathVariable ("idFilma") int idFilma){
        return glumacService.prikaziGlumceIzOdredjenogFilma(idFilma);
    }

    @GetMapping("/showGlumceFromSerija/{idSerije}")
    public List<Glumac> prikaziGlumceIzOdredjeneSerije(@PathVariable ("idSerije") int idSerije){
        return glumacService.prikaziGlumceIzOdredjeneSerije(idSerije);
    }

    @PostMapping("/addGlumcaUBazu")
    public void dodajGlumcaUBazu(@RequestBody Glumac glumac){
        glumacService.dodajGlumcaUBazu(glumac);
    }

    @PostMapping("/addGlumcaUFilm/{idFilma}/{idGlumca}")
    public void dodajGlumcaUFilm(@PathVariable ("idFilma") int idFilma, @PathVariable ("idGlumca") int idGlumca){
        glumacService.dodajGlumcaUFilm(idFilma, idGlumca);
    }

    @PostMapping("/addGlumcaUSeriju/{idSerije}/{idGlumca}")
    public void dodajGlumcaUSeriju(@PathVariable ("idSerije") int idSerije, @PathVariable ("idGlumca") int idGlumca){
        glumacService.dodajGlumcaUSeriju(idSerije, idGlumca);
    }

    @PutMapping("/changeGlumac/{idGlumca}")
    public void izmeniGlumca(@RequestBody Glumac glumac, @PathVariable ("idGlumca") int idGlumca){
        glumacService.izmeniGlumca(glumac,idGlumca);
    }

    @DeleteMapping("/deleteGlumca/{idGlumca}")
    public void obrisiGlumca(@PathVariable ("idGlumca") int idGlumca){
        glumacService.obrisiGlumca(idGlumca);
    }








}
