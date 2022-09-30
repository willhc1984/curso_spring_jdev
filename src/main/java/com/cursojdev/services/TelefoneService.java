package com.cursojdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojdev.model.Telefone;
import com.cursojdev.repositories.TelefoneRepository;

@Service
public class TelefoneService {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public List<Telefone> buscarTodos(){
		return telefoneRepository.findAll();
	}
	
	public Telefone buscarPorId(Long id) {
		Optional<Telefone> telefone = telefoneRepository.findById(id);
		return telefone.get();
	}
	
	public void deletarPorId(Long id) {
		telefoneRepository.deleteById(id);
	}
	
	public Telefone salvar(Telefone telefone){
		return telefoneRepository.save(telefone);
	}

}
