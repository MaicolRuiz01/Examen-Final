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
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrenador {
	
	@Id
	@SequenceGenerator(name="entrenador_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "entrenador_id_seq")
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private Date fecha_nacimiento;
	private Date fecha_viculacion;
	
	@OneToMany(mappedBy = "entrenador")
	private List<Captura> capturas = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="pueblo_id")
	private Pueblo pueblo;
	
	
	

}
