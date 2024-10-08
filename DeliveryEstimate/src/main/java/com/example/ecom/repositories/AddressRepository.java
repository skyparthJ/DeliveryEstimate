package com.example.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{

}
