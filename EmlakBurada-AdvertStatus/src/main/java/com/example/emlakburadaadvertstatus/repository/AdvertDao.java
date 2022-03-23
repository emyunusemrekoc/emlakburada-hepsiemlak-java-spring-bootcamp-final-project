package com.example.emlakburadaadvertstatus.repository;


import com.example.emlakburadaadvertstatus.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertDao extends JpaRepository<Advert, Integer> {


}