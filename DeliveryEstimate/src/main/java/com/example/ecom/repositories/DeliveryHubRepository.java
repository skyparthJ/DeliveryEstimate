package com.example.ecom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ecom.models.DeliveryHub;

@Repository
public interface DeliveryHubRepository extends JpaRepository<DeliveryHub,Integer>{
    /*@Query(value = "SELECT dh FROM DeliveryHub dh WHERE dh.zipCode = :zipCode")
    DeliveryHub findbyZipCode(@Param("zipCode") String zipCode);*/
}
