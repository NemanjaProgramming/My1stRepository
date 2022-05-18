package com.example.projekat_video_klub_nemanja_karapandza.Repository;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Klijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent, Integer> {

    public boolean existsById(int idKlijenta);
    public Klijent deleteById(int idKlijenta);

}
