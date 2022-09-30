package com.cursojdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursojdev.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
