package com.example.projekat_video_klub_nemanja_karapandza.Controller;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Ocena;
import com.example.projekat_video_klub_nemanja_karapandza.Service.OcenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class OcenaController {

    private final OcenaService ocenaService;

    @GetMapping("/showAllOcene")
    public List<Ocena> prikaziSveOcene() {
        return ocenaService.prikaziSveOcene();
    }

    @GetMapping("/showOceneFromVideoKlub/{imeVideoKluba}")
    public List prikaziOceneIzOdredjenogVideokluba(@PathVariable("imeVideoKluba") String imeVideoKluba){
        return ocenaService.prikaziOceneVideoKluba(imeVideoKluba);
    }

    @GetMapping("/showOcenaFromFilm/{imeFilma}")
    public List prikaziOceneIzOdredjenogFilma(@PathVariable ("imeFilma") String imeFilma){
        return ocenaService.prikaziOceneFilma(imeFilma);
    }

    @GetMapping("/showOcenaFromSerija/{imeSerije}")
    public List prikaziOceneIzOdredjeneSerije(@PathVariable ("imeSerije") String imeSerije){
        return ocenaService.prikaziOceneSerije(imeSerije);
    }

    @GetMapping("/vratiProsecnuOcenu/{idFilma}")
    public String vratiProsecnuOcenuFilma(@PathVariable("idFilma") int idFilma){
        return ocenaService.vratiProsecnuOcenuIzFilma(idFilma);
    }


    @PostMapping("/commFilm/{idFilma}")
    public void oceniFilm(@RequestBody Ocena ocena,@PathVariable("idFilma") int idFilma){
        ocenaService.oceniFilm(ocena,idFilma);
    }

    @PostMapping("/commSeriju/{imeSerije}")
    public void oceniSeriju(@RequestBody Ocena ocena,@PathVariable("imeSerije") String imeSerije){
        ocenaService.oceniSeriju(ocena,imeSerije);
    }

    @PostMapping("/commVideoKlub/{imeVideoKluba}")
    public void oceniVideoklub(@RequestBody Ocena ocena,@PathVariable("imeVideoKluba") String imeVideoKluba){
        ocenaService.oceniVideoKlub(ocena,imeVideoKluba);
    }

    @PutMapping("/changeOcenu/{idOcene}")
    public void izmeniOcenu(@RequestBody Ocena ocena, @PathVariable ("idOcene") int idOcene){
        ocenaService.izmeniOcenu(ocena,idOcene);
    }

    @DeleteMapping("/deleteCommFilma/{imeFilma}/{idOcene}")
    public void obrisiOcenuFilma(@PathVariable("imeFilma") String imeFilma,@PathVariable("idOcene") int idOcene){
        ocenaService.obrisiOcenuFilma(imeFilma,idOcene);
    }

    @DeleteMapping("/deleteCommSerije/{imeSerije}/{idOcene}")
    public void obrisiOcenuSerije(@PathVariable("imeSerije") String imeSerije,@PathVariable("idOcene") int idOcene){
        ocenaService.obrisiOcenuSerije(imeSerije,idOcene);
    }

    @DeleteMapping("/deleteCommVideoKluba/{imeVideoKluba}/{idOcene}")
    public void obrisiOcenuVideoKluba(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("idOcene") int idOcene){
        ocenaService.obrisiOcenuVideokluba(imeVideoKluba,idOcene);
    }


}
