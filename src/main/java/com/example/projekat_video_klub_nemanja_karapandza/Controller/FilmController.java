package com.example.projekat_video_klub_nemanja_karapandza.Controller;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Film;
import com.example.projekat_video_klub_nemanja_karapandza.Service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class FilmController {

    private final FilmService filmService;

    @GetMapping("/showAllFilmove")
    public List<Film> prikaziSveFilmove() {
        return filmService.prikaziSveFilmove();
    }

    @GetMapping("/showAllByIme/{imeFilma}")
    public List<Film> prikaziSvePoImenu(@PathVariable("imeFilma") String imeFilma){
        return filmService.pretragaSvihFilmovaPoImenu(imeFilma);
    }

    @GetMapping("/showAllByZanr/{zanrFilma}")
    public List<Film> prikaziSvePoZanru(@PathVariable("zanrFilma") String zanrFilma){
        return filmService.pretragaSvihFilmovaPoZanru(zanrFilma);
    }

    @GetMapping("/showAllByGodinaSnimanja/{godinaSnimanjaFilma}")
    public List<Film> prikaziSvePoGodiniSnimanja(@PathVariable("godinaSnimanjaFilma") int godinaSnimanjaFilma){
        return filmService.pretragaSvihFilmovaPoGodini(godinaSnimanjaFilma);
    }

    @GetMapping("/showAllByCenaRentiranja/{cenaRentiranjaFilma}")
    public List<Film> prikaziSvePoCeniRentiranja(@PathVariable("cenaRentiranjaFilma") int cenaRentiranjaFilma){
        return filmService.pretragaSvihFilmovaPoCeni(cenaRentiranjaFilma);
    }

    @GetMapping("/showFilmFromVideoKlub/{idVideoKluba}")
    public List<Film> prikaziFilmIzOdredjenogVideokluba(@PathVariable ("idVideoKluba") int idVideoKluba){
        return filmService.prikaziFilmoveIzOdredjenogVideoKluba(idVideoKluba);
    }

    @GetMapping("/showByZanr/{idVideoKluba}/{zanrFilma}")
    public List<Film> prikaziPoZanru(@PathVariable("idVideoKluba") int idVideoKluba,@PathVariable("zanrFilma") String zanrFilma){
        return filmService.prikaziFilmovePoZanru(idVideoKluba, zanrFilma);
    }

    @GetMapping("/showByGodinaSnimanja/{imeVideoKluba}/{godinaSnimanja}")
    public List<Film> prikaziPoGodiniSnimanja(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("godinaSnimanja") int godinaSnimanja){
        return filmService.prikaziFilmovePoGodiniSnimanja(imeVideoKluba, godinaSnimanja);
    }

    @GetMapping("/showByCenaRentiranja/{imeVideoKluba}/{cenaRentiranja}")
    public List<Film> prikaziPoCeniRentiranja(@PathVariable("imeVideoKluba") String imeVideoKluba,@PathVariable("cenaRentiranja") int cenaRentiranja){
        return filmService.prikaziFilmovePoCeniRentiranja(imeVideoKluba, cenaRentiranja);
    }

    @PostMapping("/addFilmUBazu")
    public void dodajFilmUBazu(@RequestBody Film film){
        filmService.dodajFilmUBazu(film);
    }

    @PostMapping("/addFilmUVideoKlub/{idVideoKluba}/{idFilma}")
    public void dodajFilmUVideoKlub(@PathVariable ("idVideoKluba") int idVideoKluba, @PathVariable ("idFilma") int idFilma){
        filmService.dodajFilmUVideoKlub(idVideoKluba,idFilma);
    }

    @PutMapping("/changeFilm/{idFilma}")
    public void izmeniFilm(@RequestBody Film film, @PathVariable ("idFilma") int idFilma){
        filmService.izmeniFilm(film,idFilma);
    }

    @DeleteMapping("/deleteFilm/{idFilma}")
    public void obrisiFilm(@PathVariable ("idFilma") int idFilma){
        filmService.obrisiFilm(idFilma);
    }

    @DeleteMapping("/deleteFilmIzVideoKluba/{idVideoKluba}/{idFilma}")
    public void obrisiFilmIzVideoKluba(@PathVariable("idVideoKluba") int idVideoKluba,@PathVariable("idFilma") int idFilma){
        filmService.obrisiFilmIzOdredjenogVideoKluba(idVideoKluba, idFilma);
    }



}
