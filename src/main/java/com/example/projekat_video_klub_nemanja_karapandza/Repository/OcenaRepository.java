package com.example.projekat_video_klub_nemanja_karapandza.Repository;

import com.example.projekat_video_klub_nemanja_karapandza.Entities.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcenaRepository extends JpaRepository<Ocena,Integer> {


}
