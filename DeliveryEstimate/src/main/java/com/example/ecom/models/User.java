package com.example.ecom.models;

import lombok.Data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "user1")
public class User extends BaseModel{
    private String name;
    private String email;
    @OneToMany
    private List<Address> addresses;
}
