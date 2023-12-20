package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Pokemon;
import com.example.demo.repository.RepoPokemon;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
	
	@Autowired
    private RepoPokemon pokemonRepository;

    @GetMapping("/{tipo}/pokemons")
    public ResponseEntity<List<Pokemon>> listarPokemonesPorTipo(@PathVariable("tipo") String tipo) {
        
        List<Pokemon> pokemones = pokemonRepository.findByTipoPokemon_Descripcion(tipo);

        if (pokemones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pokemones);
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<Pokemon> registrarPokemon(@RequestBody Pokemon nuevoPokemon) {
        
        if (nuevoPokemon.getUuid() != null && pokemonRepository.findById(nuevoPokemon.getId()) != null) {
            return ResponseEntity.badRequest().body(null); // O podrías lanzar una excepción indicando que ya existe
        }

        Pokemon pokemonGuardado = pokemonRepository.save(nuevoPokemon);
        return ResponseEntity.ok(pokemonGuardado);
    }
    
    

}
