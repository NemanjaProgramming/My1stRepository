package com.example.projekat_video_klub_nemanja_karapandza.Repository;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.VideoKlub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoKlubRepository extends JpaRepository<VideoKlub, Integer> {

    public VideoKlub findByIme(String imeFilma);
    public Boolean existsByIme(String imeFilma);
}
