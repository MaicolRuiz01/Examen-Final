package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Pokemon;

public interface RepoPokemon extends JpaRepository<Pokemon, Integer> {

}
