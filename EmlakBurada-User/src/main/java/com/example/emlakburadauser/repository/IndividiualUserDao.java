package com.example.emlakburadauser.repository;

import com.example.emlakburadauser.model.IndividualUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividiualUserDao extends JpaRepository<IndividualUser, Integer>{

    IndividualUser findById(int id);

}
