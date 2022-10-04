package com.cursojdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursojdev.model.Usuario;
import com.cursojdev.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> buscarTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.get();
	}
	
	public void deletarPorId(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario salvar(Usuario usuario){
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioRepository.save(usuario);
	}

}
