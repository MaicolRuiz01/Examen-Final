package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Entrenador;
import com.example.demo.repository.RepoEntrenador;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {
	
	@Autowired
	RepoEntrenador repoentrenador;
	
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

}
