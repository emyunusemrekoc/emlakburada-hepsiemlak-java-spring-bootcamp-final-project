package com.example.emlakburadaadvert.repository;

import com.example.emlakburadaadvert.model.Advert;
import com.example.emlakburadaadvert.model.enums.AdvertStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertDao extends JpaRepository<Advert, Integer> {

    Advert findById(int id);
    List<Advert> findAllByAdvertStatus(AdvertStatus advertStatus);
    List<Advert> findAllByUserIdAndAdvertStatus(int userId,AdvertStatus advertStatus);

}