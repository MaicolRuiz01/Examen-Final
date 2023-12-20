package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        // Suponiendo que tienes un método en el repositorio para buscar Pokemones por tipo
        List<Pokemon> pokemones = pokemonRepository.findByTipoPokemon_Descripcion(tipo);

        if (pokemones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pokemones);
    }

}
