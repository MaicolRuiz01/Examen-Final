package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Captura;
import com.example.demo.entities.Entrenador;

public interface RepoCaptura extends JpaRepository<Captura, Integer> {
	List<Captura> findByEntrenador(Entrenador entrenador);

}
