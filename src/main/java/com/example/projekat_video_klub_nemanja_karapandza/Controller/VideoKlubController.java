package com.example.projekat_video_klub_nemanja_karapandza.Controller;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.VideoKlub;
import com.example.projekat_video_klub_nemanja_karapandza.Service.VideoklubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VideoKlubController {

    private final VideoklubService videoklubService;

    @PostMapping("/addVideoKlub")
    public void dodajVideoKlub(@RequestBody VideoKlub videoKlub){
        videoklubService.dodajVideoKlub(videoKlub);
    }

    @PostMapping("/iznajmiFilm/{idKlijenta}/{idFilma}")
    public void iznajmiFilm(@PathVariable("idKlijenta") int idKlijenta,@PathVariable("idFilma") int idFilma){
        videoklubService.iznajmiFilm(idKlijenta, idFilma);
    }

    @PostMapping("/iznajmiSeriju/{idKlijenta}/{idSerije}")
    public void iznajmiSeriju(@PathVariable("idKlijenta") int idKlijenta,@PathVariable("idSerije") int idSerije){
        videoklubService.iznajmiSeriju(idKlijenta, idSerije);
    }

    @GetMapping("/videoKlubShowAll")
    public List<VideoKlub> prikaziSveVideoKlubove(){
        return videoklubService.prikaziSveVideoKlubove();
    }

    @PutMapping("/changeVideoKlub/{imeVideoKluba}")
    public void izmeniVideoKlub(@RequestBody VideoKlub videoKlub, @PathVariable ("imeVideoKluba") String imeVideoKluba){
        videoklubService.promeniVideoKlub(videoKlub,imeVideoKluba);
    }

    @DeleteMapping("/deleteVideoKlub/{idVideoKluba}")
    public void obrisiVideoKlub(@PathVariable ("idVideoKluba") int idVideoKluba){
        videoklubService.obrisiVideoKlub(idVideoKluba);
    }







}
