package com.cursojdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursojdev.model.Telefone;
import com.cursojdev.services.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {
	
	@Autowired
	private TelefoneService telefoneService;
	
	@GetMapping
	public ResponseEntity<List<Telefone>> buscarTodos(){
		List<Telefone> telefones = telefoneService.buscarTodos();
		return ResponseEntity.ok(telefones);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Telefone> buscarPorId(@PathVariable Long id){
		Telefone telefone = telefoneService.buscarPorId(id);
		return ResponseEntity.ok(telefone);
	}
	
	@PostMapping
	public ResponseEntity<Telefone> salvar(@RequestBody Telefone telefone){
		telefone = telefoneService.salvar(telefone);
		return ResponseEntity.ok(telefone);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
		telefoneService.deletarPorId(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Telefone> atualizar(@RequestBody Telefone telefone){
		telefone = telefoneService.salvar(telefone);
		return ResponseEntity.ok(telefone);
	}
	
}
