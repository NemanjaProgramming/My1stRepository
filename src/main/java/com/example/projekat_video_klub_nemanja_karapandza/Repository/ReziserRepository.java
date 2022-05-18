package com.example.projekat_video_klub_nemanja_karapandza.Repository;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Reziser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReziserRepository extends JpaRepository<Reziser,Integer> {

    @Query("SELECT r from  Reziser r WHERE r.ime=?1 OR r.prezime=?1")
    public List<Reziser> pretraziRezisere(String parametarZaPretragu);



}
