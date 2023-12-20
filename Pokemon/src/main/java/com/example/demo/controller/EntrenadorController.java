package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Captura;
import com.example.demo.entities.Entrenador;
import com.example.demo.entities.Pokemon;
import com.example.demo.repository.RepoCaptura;
import com.example.demo.repository.RepoEntrenador;
import com.example.demo.repository.RepoPokemon;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {
	
	@Autowired
	RepoEntrenador repoentrenador;
	@Autowired
    RepoCaptura capturaRepository;
	@Autowired
	RepoPokemon pokemonRepository;
	
	 @PostMapping("/login")
	    public ResponseEntity<Map<String, String>> obtenerUUIDPorEmail(@RequestBody Map<String, String> requestBody) {
	        String email = requestBody.get("email");

	        // Lógica para obtener el entrenador por email
	        Entrenador entrenador = repoentrenador.findByEmail(email);

	        if (entrenador == null) {
	            return ResponseEntity.notFound().build();
	        }

	        Map<String, String> response = new HashMap<>();
	        response.put("uuid", entrenador.getUuid()); // Suponiendo que la entidad Entrenador tiene un campo uuid

	        return ResponseEntity.ok(response);
	    }
	 
	 

	    @GetMapping("/{uuid}/pokemons")
	    public ResponseEntity<List<Pokemon>> listarPokemonesDeEntrenador(@PathVariable("uuid") String uuid) {
	        Entrenador entrenador = repoentrenador.findByUuid(uuid);

	        if (entrenador == null) {
	            return ResponseEntity.notFound().build();
	        }

	        List<Captura> capturas = capturaRepository.findByEntrenador(entrenador);
	        List<Pokemon> pokemones = capturas.stream().map(Captura::getPokemones).collect(Collectors.toList());

	        return ResponseEntity.ok(pokemones);
	    }
	    
	    @PostMapping("/{entrenadorUuid}/pokemons/{pokemonUuid}")
	    public ResponseEntity<Map<String, String>> agregarPokemonAEntrenador(
	            @PathVariable("entrenadorUuid") String entrenadorUuid,
	            @PathVariable("pokemonUuid") String pokemonUuid
	    ) {
	        Entrenador entrenador = repoentrenador.findByUuid(entrenadorUuid);
	        Pokemon pokemon = pokemonRepository.findByUuid(pokemonUuid);

	        if (entrenador == null || pokemon == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Verificar si el Pokemon ya está asociado al Entrenador
	        boolean yaAsociado = capturaRepository.existsByEntrenadorAndPokemon(entrenador, pokemon);

	        if (yaAsociado) {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("error", "true");
	            errorResponse.put("message", "El pokemon ya está registrado al entrenador");
	            return ResponseEntity.badRequest().body(errorResponse);
	        }

	        Captura nuevaCaptura = new Captura();
	        nuevaCaptura.setEntrenador(entrenador);
	        nuevaCaptura.setPokemones(pokemon);
	        capturaRepository.save(nuevaCaptura);

	        return ResponseEntity.ok().build();
	    }
	    

}
