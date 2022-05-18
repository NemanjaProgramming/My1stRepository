package com.example.projekat_video_klub_nemanja_karapandza.Repository;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Serija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerijaRepository extends JpaRepository<Serija,Integer> {

    public boolean existsByIme(String imeSerije);
    public Serija findByIme(String imeSerije);

}
