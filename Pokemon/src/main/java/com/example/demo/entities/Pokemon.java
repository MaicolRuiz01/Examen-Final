package com.example.demo.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor

public class Pokemon {
	
	@Id
	@SequenceGenerator(name="pokemon_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pokemon_id_seq")
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha_descubrimiento;
	@ManyToOne
	@JoinColumn(name="tipo_pokemon")
	private TipoPokemon tipoPokemon;
	private Integer generacion;
	private String uuid;
	@OneToMany(mappedBy = "pokemon")
	private List<Captura> capturas = new ArrayList<>();

	
	
	
	
}
