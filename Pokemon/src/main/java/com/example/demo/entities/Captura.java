package com.example.demo.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Captura {
	
	
	@ManyToOne
	@JoinColumn(name="pokemon_id")
	private Pokemon pokemones;
	@ManyToOne
	@JoinColumn(name="entrenador_id")
	private Entrenador entrenador;

}
